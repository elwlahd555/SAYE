<template>
  <section class="section">
    <v-container mt-0>
      <v-row v-if="!isAlbumLoading && albums.length > 0">
        <v-col cols="6">
          <span
            class="is-size-5-desktop is-size-6-mobile has-text-grey"
            v-if="pageType !== 'bookmarks'"
          >
            Search Results
          </span>
          <span class="is-size-5-desktop is-size-6-mobile has-text-grey" v-else>
            Bookmarks</span
          >
        </v-col>
        <v-col cols="5" class="text-right">
          <span class="has-text-grey-light is-size-6">
            {{ albums.length }} album(s)
          </span>
        </v-col>
        <v-col cols="1" class="text-left">
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-icon @click="onClickUpdateSettings" v-bind="attrs" v-on="on">{{
                panelIcon
              }}</v-icon>
            </template>
            <span>switch panel view</span>
          </v-tooltip>
        </v-col>
      </v-row>
      <!-- Album List -->
      <transition name="list" mode="out-in">
        <v-row
          v-if="!isAlbumLoading && displayedAlbums.length > 0"
          :key="pageType"
        >
          <v-col
            v-for="album in displayedAlbums"
            :key="album.mId"
            :cols="colSize"
          >
            <!-- Card Panel  -->
            <v-card v-if="settings.panelType === 'card'">
              <v-img
                :src="album.mImg"
                lazy-src="https://picsum.photos/id/11/100/60"
                :alt="album.mImg"
                max-height="200"
              >
                <template v-slot:placeholder>
                  <v-row
                    class="fill-height ma-0"
                    align="center"
                    justify="center"
                  >
                    <v-progress-circular
                      indeterminate
                      color="grey lighten-5"
                    ></v-progress-circular>
                  </v-row>
                </template>
              </v-img>
              <v-card-title class="pt-1">
                <a v-if="album.mId" @click="onClickAlbumName(album.mId)">
                  <span
                    class="d-inline-block text-truncate"
                    style="max-width: 200px"
                    >{{ album.mTitle }}</span
                  >
                </a>
              </v-card-title>
              <v-card-subtitle class="py-0">
                <span
                  class="d-inline-block text-truncate "
                  style="max-width: 200px"
                >
                  {{ album.mAlbum }}
                </span>
              </v-card-subtitle>
              <v-card-subtitle class="py-0">
                <span
                  class="d-inline-block text-truncate"
                  style="max-width: 200px"
                >
                  {{ album.mArtist }}
                </span>
              </v-card-subtitle>
              <v-card-text>
                <span
                  class="d-inline-block text-truncate"
                  style="max-width: 200px"
                >
                  {{ album.mGenre }}
                </span>
              </v-card-text>

              <!-- 버튼 3개 (북마크, 플레이리스트, youtube)-->
              <v-card-actions>
                <!-- 북마크 -->
                <v-tooltip bottom>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon
                      color="warning"
                      large
                      v-bind="attrs"
                      v-on="on"
                      @click="clickBookmarkAlbum(album)"
                    >
                      {{ settings.bookmarkIcon
                      }}{{ isInBookmark(album.mId) ? "" : "-outline" }}
                    </v-icon>
                  </template>
                  <span>북마크 추가/제거</span>
                </v-tooltip>

                <v-spacer></v-spacer>

                <!-- 플레이리스트 -->
                <v-tooltip bottom>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon
                      color="primary"
                      large
                      v-bind="attrs"
                      v-on="on"
                      @click="clickBookmarkAlbum(album)"
                    >
                      mdi-playlist-music
                    </v-icon>
                  </template>
                  <span>플레이리스트 추가/제거</span>
                </v-tooltip>

                <v-spacer v-if="settings.youtubeLink === 'true'"></v-spacer>
                <span v-if="settings.youtubeLink === 'true'">
                  <!-- 유튜브 -->
                  <v-tooltip bottom>
                    <template v-slot:activator="{ on, attrs }">
                      <v-icon
                        color="error"
                        large
                        v-bind="attrs"
                        v-on="on"
                        @click="handleClick(album)"
                      >
                        mdi-youtube
                      </v-icon>
                    </template>
                    <span>유튜브 검색</span>
                  </v-tooltip>
                </span>
              </v-card-actions>
            </v-card>
            <!-- Media Panel-->
            <article v-if="settings.panelType === 'media'">
              <v-list two-line>
                <v-list-item>
                  <v-img
                    :src="album.mImg"
                    :alt="album.mAlbum"
                    style="border-radius: 50%; 
                            max-height: 100px;
                            max-width: 100px;"
                  />

                  <v-list-item-content class="ml-12">
                    <v-list-item-title>{{ album.mTitle }}</v-list-item-title>

                    <v-list-item-subtitle>
                      {{ album.mAlbum }}
                    </v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-action class="mr-12">
                    <v-row>
                      <v-tooltip bottom>
                        <template v-slot:activator="{ on, attrs }">
                          <v-icon
                            color="warning"
                            large
                            v-bind="attrs"
                            v-on="on"
                            @click="clickBookmarkAlbum(album)"
                          >
                            {{ settings.bookmarkIcon
                            }}{{ isInBookmark(album.mId) ? "" : "-outline" }}
                          </v-icon>
                        </template>
                        <span>북마크 추가/제거</span>
                      </v-tooltip>

                      <v-tooltip bottom>
                        <template v-slot:activator="{ on, attrs }">
                          <v-icon
                            color="primary"
                            large
                            v-bind="attrs"
                            v-on="on"
                            @click="clickBookmarkAlbum(album)"
                          >
                            mdi-playlist-music
                          </v-icon>
                        </template>
                        <span>플레이리스트 추가/제거</span>
                      </v-tooltip>

                      <v-tooltip bottom>
                        <template v-slot:activator="{ on, attrs }">
                          <v-icon
                            color="error"
                            large
                            v-bind="attrs"
                            v-on="on"
                            @click="handleClick(album)"
                          >
                            mdi-youtube
                          </v-icon>
                        </template>
                        <span>유튜브 검색</span>
                      </v-tooltip>
                    </v-row>
                  </v-list-item-action>
                </v-list-item>
              </v-list>
            </article>
          </v-col>
        </v-row>
      </transition>

      <!-- Loading animation -->
      <v-row class="is-mobile" v-if="isAlbumLoading">
        <v-col class="loading">
          <v-progress-circular
            :is-full-page="false"
            :active.sync="isAlbumLoading"
            :can-cancel="false"
            indeterminate
          ></v-progress-circular>
        </v-col>
      </v-row>
      <!-- Pagination -->
      <v-row v-if="!isAlbumLoading && albums.length > 0">
        <v-col cols="12" v-if="albums.length > 0">
          <hr />
          <v-pagination
            v-model="current"
            :length="Math.floor(this.albums.length / this.settings.perPage)"
            :total-visible="settings.perPage"
            circle
          >
          </v-pagination>
        </v-col>
      </v-row>
      <!-- No Bookmark message-->
      <template v-if="pageType === 'bookmarks' && albums.length === 0">
        <v-row>
          <v-col>
            <h3 class="title text-center">You have no saved bookmarks.</h3>
          </v-col>
        </v-row>
      </template>
      <!-- Search results message -->
      <template v-if="searchFailed && !isAlbumLoading">
        <v-row>
          <v-col>
            <h3 class="title text-center">Nothing found.</h3>
            <h3 class="title text-center">Please Search Again!</h3>
          </v-col>
        </v-row>
      </template>
    </v-container>
  </section>
</template>

<script>
import getYouTubeID from "get-youtube-id";

export default {
  name: "AlbumList",
  data() {
    return {
      current: 1,
      size: "",
      panelIcon: "mdi-grid"
    };
  },
  props: {
    albums: {
      type: Array,
      required: true
    },
    pageType: {
      type: String,
      required: true
    },
    isAlbumLoading: {
      type: Boolean,
      required: true
    },
    searchFailed: {
      type: Boolean,
      required: true
    },
    bookmarkAlbums: {
      type: Array,
      required: true
    },
    settings: {
      type: Object,
      required: true
    },
    isMobile: {
      type: Boolean,
      required: true
    },
    clickBookmarkAlbum: {
      type: Function,
      required: true
    },
    isInBookmark: {
      type: Function,
      required: true
    }
  },
  computed: {
    displayedAlbums() {
      return this.paginate(this.albums);
    },
    colSize() {
      return this.settings.panelType === "card" ? "3" : "12";
    }
  },
  watch: {
    albums(val, oldVal) {
      if (val !== oldVal) {
        this.current = 1;
      }
    }
  },
  methods: {
    paginate(albums) {
      let current = this.current;
      let perPage = this.settings.perPage;
      let from = current * perPage - perPage;
      let to = current * perPage;
      return albums.slice(from, to);
    },
    onClickUpdateSettings() {
      const settingValue =
        this.settings.panelType === "card" ? "media" : "card";
      this.panelIcon =
        this.panelIcon === "mdi-grid" ? "mdi-format-list-text" : "mdi-grid";
      this.$emit("clickUpdateSettings", "panelType", settingValue);
    },
    onClickAlbumName(albumId) {
      this.$emit("clickAlbumName", albumId);
    },
    handleClick(music) {
      let videoId = getYouTubeID(music.mUrl);
      if (!videoId) {
        console.log("nothing...");
        const url = `https://www.youtube.com/results?search_query=${music.mArtist} - ${music.mTitle}`;
        const strWindowFeatures =
          "location=yes,height=800,width=600,scrollbars=yes,status=yes";
        window.open(url, "_blank", strWindowFeatures);
      } else {
        this.$store.dispatch("setVideoId", videoId);
        this.$store.dispatch("setPlayMusic", music);
        this.$store.dispatch("addToPlaylist", music);
      }
    }
  }
};
</script>
