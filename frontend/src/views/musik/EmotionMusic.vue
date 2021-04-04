<template>
  <v-container fluid pa-0>
    <div class="nav-search">
      <the-navbar
        v-show="showNavbar"
        @clickShowBookmarks="showBookmarks"
        @clickSettings="switchDialog"
        @clickTitle="setPageType('search')"
        @clickAlbumName="getAlbumTracks"
        :pageType="pageType"
        :recentSearch="recentSearch"
        :bookmarkAlbums="bookmarkAlbums"
        :settings="settings"
        :isMobile="isMobile"
      >
      </the-navbar>
    </div>
    <main>
      <album-list
        @clickUpdateSettings="updateSettings"
        @clickAlbumName="getAlbumTracks"
        :clickBookmarkAlbum="bookmarkAlbum"
        :isInBookmark="isInBookmark"
        :albums="pageType === 'search' ? albums : bookmarkAlbums"
        :pageType="pageType"
        :isAlbumLoading="isAlbumLoading"
        :searchFailed="searchFailed"
        :bookmarkAlbums="bookmarkAlbums"
        :settings="settings"
        :isMobile="isMobile"
      >
      </album-list>
      <!-- album tracklist modal -->
      <v-dialog
        :active.sync="isAlbumTracksModalActive"
        :canCancel="true"
        has-modal-card
        :onCancel="resetAlbumTracks"
        scroll="clip"
      >
        <v-row v-if="!albumTracksFailed && albumTracks.length === 0">
          <v-col>
            <div class="column loading">
              <v-progress-circular
                indeterminate
                :active.sync="isAlbumTracksLoading"
              ></v-progress-circular>
            </div>
          </v-col>
        </v-row>
        <v-container v-else-if="albumTracksFailed">
          <v-row>
            <v-col cols="4">
              <v-alert type="warning" border="top" colored-border>
                Error loading album track list. <br />
                Please check again later.
              </v-alert>
            </v-col>
          </v-row>
        </v-container>
        <album-track-list
          v-else
          :albumTracks="albumTracks"
          :clickBookmarkAlbum="bookmarkAlbum"
          :isInBookmark="isInBookmark"
          :settings="settings"
          :isMobile="isMobile"
        >
        </album-track-list>
      </v-dialog>
    </main>

    <v-snackbar v-model="snackbar" :timeout="2000" :color="snackbarColor">
      {{ snackbarText }}
      <template v-slot:action="{ attrs }">
        <v-btn dark text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import Swal from "sweetalert2";

import TheNavbar from "@/components/emotionBaseRecommendation/TheNavbar";
import AlbumList from "@/components/emotionBaseRecommendation/AlbumList";
import AlbumTrackList from "@/components/basicSearch/AlbumTrackList";
import { mapGetters } from "vuex";

const albumStore = "albumStore";

export default {
  name: "app",
  data() {
    return {
      //initialSearchQuery: "",
      isSettingsModalActive: false,
      isAlbumTracksModalActive: false,
      windowWidth: window.innerWidth,
      showNavbar: true,
      snackbar: false,
      snackbarText: "",
      snackbarColor: ""
    };
  },
  components: {
    TheNavbar,
    AlbumList,
    AlbumTrackList
  },
  computed: {
    ...mapGetters(albumStore, {
      recentSearch: "GET_RECENT_SEARCH",
      albums: "GET_ALBUMS",
      albumTracks: "GET_ALBUM_TRACKS",
      searchQuery: "SEARCH_QUERY",
      initialSearchQuery: "INITIAL_SEARCH_QUERY",
      bookmarkAlbums: "BOOKMARK_ALBUMS",
      pageType: "PAGE_TYPE",
      isAlbumLoading: "IS_ALBUM_LOADING",
      isAlbumTracksLoading: "IS_ALBUM_TRACKS_LOADING",
      searchFailed: "SEARCH_FAILED",
      albumTracksFailed: "ALBUM_TRACKS_FAILED",
      settings: "GET_SETTINGS",
      isAppError: "IS_APP_ERROR"
    }),
    isMobile() {
      return this.$mq === "mobile";
    }
  },
  created() {
    this.$store.dispatch(albumStore + "/GET_SETTINGS");
    this.$store.dispatch(albumStore + "/GET_RECENT_SEARCH");
    this.$store.dispatch(albumStore + "/GET_BOOKMARK_ALBUMS");
    window.scrollTo(0, 0);
  },
  methods: {
    searchAlbums(query) {
      if (query) {
        const payload = {
          // iTunes api를 활용한 url
          // url: `/api/search?term=${query}&entity=album&media=music`,
          url: `/spotify/search?q=${query}`,
          query: query
        };
        this.$store.dispatch(albumStore + "/SEARCH_ALBUMS", payload);
      }
      this.$store.commit(albumStore + "/SET_PAGE_TYPE", "search");
    },
    clearSearch() {
      this.$store.commit(albumStore + "/CLEAR_SEARCH");
    },
    removeRecentSearchItem(item) {
      this.$store.dispatch(albumStore + "/REMOVE_RECENT_SEARCH_ITEM", item);
    },
    bookmarkAlbum(album) {
      if (this.isInBookmark(album.mId)) {
        Swal.fire({
          icon: "question",
          text: `"${album.mTitle}"를(을) 북마크에서 삭제하나요?`,
          showCancelButton: true,
          confirmButtonText: `Yes`
        }).then(res => {
          if (res.isConfirmed) {
            this.$store.dispatch(albumStore + "/BOOKMARK_ALBUM", {
              album: album,
              status: "unbookmarked"
            });
            this.snackbar = true;
            this.snackbarColor = "error";
            this.snackbarText = `${album.mTitle} 북마크에서 제거`;
          }
        });
      } else {
        this.$store.dispatch(albumStore + "/BOOKMARK_ALBUM", {
          album: album,
          status: "bookmark"
        });
        this.snackbar = true;
        this.snackbarColor = "success";
        this.snackbarText = `"${album.mTitle}" 북마크에 추가`;
      }
    },
    isInBookmark(albumName) {
      return (
        this.bookmarkAlbums.findIndex(album => album.mId === albumName) > -1
      );
    },
    switchDialog() {
      this.isSettingsModalActive = !this.isSettingsModalActive;
    },
    showBookmarks() {
      this.$store.commit(albumStore + "/SET_PAGE_TYPE", "bookmarks");
    },
    updateSettings(settingName, settingValue) {
      const payload = { settingName: settingName, settingValue: settingValue };
      this.$store.dispatch(albumStore + "/UPDATE_SETTINGS", payload);
    },
    getAlbumTracks(albumId) {
      if (albumId) {
        this.isAlbumTracksModalActive = true;
        const payload = { url: `/api/lookup?id=${albumId}&entity=song` };
        this.$store.dispatch(albumStore + "/GET_ALBUM_TRACKS", payload);
      }
    },
    setPageType(pageType) {
      if (pageType !== this.pageType) {
        this.$store.commit(albumStore + "/SET_PAGE_TYPE", pageType);
      }

      if (
        pageType === "search" &&
        this.initialSearchQuery !== this.searchQuery
      ) {
        this.searchAlbums(this.initialSearchQuery);
      }
    },
    resetAlbumTracks() {
      this.$store.commit(albumStore + "/RESET_ALBUM_TRACKS");
    }
    // toggleNavbar() {
    //   let scrollBarPosition = window.pageYOffset | document.body.scrollTop;
    //   if (scrollBarPosition > 100) {
    //     this.showNavbar = false;
    //   } else {
    //     this.showNavbar = true;
    //   }
    // },
  }
};
</script>

<style lang="scss">
// transitions
.list-enter-active {
  transition: all 0.5s;
}
.list-leave-active {
  transition: all 0.5s;
}
.list-enter,
.list-leave-to {
  opacity: 0;
  transform: translateY(20px);
}
.fade-enter-active,
.component-fade-leave-active {
  transition: opacity 0.3s ease;
}
.fade-enter,
.component-fade-leave-to {
  opacity: 0;
}
</style>
