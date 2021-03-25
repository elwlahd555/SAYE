<template>
  <div id="app">
    <div class="nav-search">
      <the-navbar
        v-show="showNavbar"
        @clickToggleRecentSearchBox="toggleRecentSearchBox"
        @clickShowBookmarks="showBookmarks"
        @clickSettings="showSettingsModal"
        @clickTitle="setPageType('search')"
        @clickAlbumName="getAlbumTracks"
        :pageType="pageType"
        :recentSearch="recentSearch"
        :bookmarkAlbums="bookmarkAlbums"
        :settings="settings"
        :isMobile="isMobile"
        :showRecentSearchBox="showRecentSearchBox"
      >
      </the-navbar>
      <the-searchbar
        @clickSearch="searchAlbums"
        @clickClearSearch="clearSearch"
        :recentSearch="recentSearch"
        :newSearchQuery="searchQuery"
        :settings="settings"
      >
      </the-searchbar>
    </div>
    <main>
      <transition name="fade">
        <recent-search-box
          v-if="showRecentSearchBox && recentSearch.length > 0"
          :recentSearch="recentSearch"
          @clickSearchItem="searchAlbums"
          @clickRemoveRecentSearchItem="removeRecentSearchItem"
        >
        </recent-search-box>
      </transition>
      <album-list
        @clickUpdateSettings="updateSettings"
        @clickAlbumName="getAlbumTracks"
        :clickBookmarkAlbum="bookmarkAlbum"
        :replaceArtworkUrlSize="replaceArtworkUrlSize"
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
      <!-- settings modal -->
      <v-dialog
        transition="dialog-top-transition"
        v-model="isSettingsModalActive"
        max-width="500"
      >
        <the-settings
          :settings="settings"
          @clickUpdateSettings="updateSettings"
        >
        </the-settings>
      </v-dialog>
      <!-- album tracklist modal -->
      <v-dialog
        :active.sync="isAlbumTracksModalActive"
        :canCancel="true"
        has-modal-card
        :onCancel="resetAlbumTracks"
        scroll="clip"
      >
        <div
          class="columns is-mobile is-centered"
          v-if="!albumTracksFailed && albumTracks.length === 0"
        >
          <div class="columns is-mobile">
            <div class="column loading">
              <b-loading
                :is-full-page="false"
                :active.sync="isAlbumTracksLoading"
                :can-cancel="false"
              ></b-loading>
            </div>
          </div>
        </div>
        <div class="container" v-else-if="albumTracksFailed">
          <div class="columns is-mobile is-centered">
            <div class="column is-4">
              <b-message type="is-danger" has-icon>
                Error loading album track list. <br />
                Please check again later.
              </b-message>
            </div>
          </div>
        </div>
        <album-track-list
          v-else
          :albumTracks="albumTracks"
          :clickBookmarkAlbum="bookmarkAlbum"
          :isInBookmark="isInBookmark"
          :replaceArtworkUrlSize="replaceArtworkUrlSize"
          :settings="settings"
          :isMobile="isMobile"
        >
        </album-track-list>
      </v-dialog>
    </main>
  </div>
</template>

<script>
import TheNavbar from "@/components/basicSearch/TheNavbar";
import TheSearchbar from "@/components/basicSearch/TheSearchbar";
import RecentSearchBox from "@/components/basicSearch/RecentSearchBox";
import AlbumList from "@/components/basicSearch/AlbumList";
import TheSettings from "@/components/basicSearch/TheSettings";
import AlbumTrackList from "@/components/basicSearch/AlbumTrackList";
import { mapGetters } from "vuex";

const albumStore = "albumStore";

export default {
  name: "app",
  data() {
    return {
      initialSearchQuery: "",
      isSettingsModalActive: false,
      isAlbumTracksModalActive: false,
      windowWidth: window.innerWidth,
      showNavbar: true,
    };
  },
  components: {
    TheNavbar,
    TheSearchbar,
    RecentSearchBox,
    TheSettings,
    AlbumList,
    AlbumTrackList,
  },
  computed: {
    ...mapGetters(albumStore, {
      recentSearch: "GET_RECENT_SEARCH",
      albums: "GET_ALBUMS",
      albumTracks: "GET_ALBUM_TRACKS",
      searchQuery: "SEARCH_QUERY",
      //initialSearchQuery: "INITIAL_SEARCH_QUERY",
      bookmarkAlbums: "BOOKMARK_ALBUMS",
      pageType: "PAGE_TYPE",
      showRecentSearchBox: "SHOW_RECENT_SEARCH_BOX",
      isAlbumLoading: "IS_ALBUM_LOADING",
      isAlbumTracksLoading: "IS_ALBUM_TRACKS_LOADING",
      searchFailed: "SEARCH_FAILED",
      albumTracksFailed: "ALBUM_TRACKS_FAILED",
      settings: "GET_SETTINGS",
      isAppError: "IS_APP_ERROR",
    }),
    showRecentSearchBox() {
      return this.$store.state.albumStore.showRecentSearchBox;
    },
    isMobile() {
      return this.$mq === "mobile";
    },
  },
  created() {
    this.$store.dispatch(albumStore + "/GET_SETTINGS");
    this.$store.dispatch(albumStore + "/GET_RECENT_SEARCH");
    this.$store.dispatch(albumStore + "/GET_BOOKMARK_ALBUMS");
    this.initialSearchQuery = this.$route.query.keyword;
    window.addEventListener("scroll", this.toggleNavbar);
    window.scrollTo(0, 0);
  },
  destroyed() {
    window.removeEventListener("scroll", this.toggleNavbar);
  },
  methods: {
    searchAlbums(query) {
      console.log(this.initialSearchQuery);
      if (query) {
        const payload = {
          url: `/api/search?term=${query}&entity=album&media=music`,
          query: query,
        };
        this.$store.dispatch(albumStore + "/SEARCH_ALBUMS", payload);
      }
      this.$store.commit(albumStore + "/SET_PAGE_TYPE", "search");
    },
    clearSearch() {
      this.$store.commit(albumStore + "/CLEAR_SEARCH");
    },
    toggleRecentSearchBox() {
      this.$store.commit(albumStore + "/TOGGLE_RECENT_SEARCH");
    },
    removeRecentSearchItem(item) {
      this.$store.dispatch(albumStore + "/REMOVE_RECENT_SEARCH_ITEM", item);
    },
    bookmarkAlbum(album) {
      if (this.isInBookmark(album.collectionCensoredName)) {
        this.$dialog.confirm({
          message: `Are you sure you want to unbookmark this album? <b>${album.collectionCensoredName} album</b>`,
          type: "is-danger",
          hasIcon: true,
          onConfirm: () => {
            this.$store.dispatch(albumStore + "/BOOKMARK_ALBUM", {
              album: album,
              status: "unbookmarked",
            });
            this.$toast.open({
              duration: 3000,
              message: `"${album.collectionCensoredName} album" has been unbookmark!`,
              position: "is-bottom-right",
              type: "is-danger",
            });
          },
        });
      } else {
        console.log("before");
        // 나중에 snackbar 추가하셈...
        // this.$toast.open({
        //   duration: 3000,
        //   message: `"${album.collectionCensoredName} album" bookmarked!`,
        //   position: "is-bottom",
        //   type: "is-info"
        // });
        this.$store.dispatch(albumStore + "/BOOKMARK_ALBUM", {
          album: album,
          status: "bookmark",
        });
        console.log("after");
      }
    },
    isInBookmark(albumName) {
      return (
        this.bookmarkAlbums.findIndex(
          (album) => album.collectionCensoredName === albumName
        ) > -1
      );
    },
    showBookmarks() {
      this.$store.commit(albumStore + "/SET_PAGE_TYPE", "bookmarks");
    },
    updateSettings(settingName, settingValue) {
      const payload = { settingName: settingName, settingValue: settingValue };
      this.$store.dispatch(albumStore + "/UPDATE_SETTINGS", payload);
    },
    showSettingsModal() {
      this.isSettingsModalActive = true;
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
    },
    replaceArtworkUrlSize(albumArtwork, newSize) {
      return albumArtwork.replace("100x100", newSize);
    },
    toggleNavbar() {
      let scrollBarPosition = window.pageYOffset | document.body.scrollTop;
      if (scrollBarPosition > 100) {
        this.showNavbar = false;
      } else {
        this.showNavbar = true;
      }
    },
  },
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
