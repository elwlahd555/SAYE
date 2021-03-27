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
            cols="3"
            v-for="album in displayedAlbums"
            :key="album.collectionId"
          >
            <!-- Card Panel  -->
            <v-card v-if="settings.panelType === 'card'">
              <v-img
                :src="replaceArtworkUrlSize(album.artworkUrl100, '300x250')"
                :alt="album.collectionCensoredName"
                max-height="200"
              />

              <v-card-title>
                <a
                  v-if="album.collectionId"
                  @click="onClickAlbumName(album.collectionId)"
                >
                  <span
                    class="d-inline-block text-truncate"
                    style="max-width: 200px"
                    >{{ album.collectionCensoredName }}</span
                  >
                </a>
              </v-card-title>
              <v-card-subtitle>
                <span
                  class="d-inline-block text-truncate"
                  style="max-width: 200px"
                >
                  {{ album.artistName }}
                </span>
              </v-card-subtitle>
              <v-card-text>
                <span
                  class="d-inline-block text-truncate"
                  style="max-width: 200px"
                >
                  {{ album.primaryGenreName }}
                </span>
              </v-card-text>

              <!-- 버튼 3개 (북마크, 플레이리스트, youtube)-->
              <v-card-actions>
                <!-- 북마크 -->
                <v-tooltip bottom>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon
                      color="warning"
                      dark
                      v-bind="attrs"
                      v-on="on"
                      @click="clickBookmarkAlbum(album)"
                    >
                      {{ settings.bookmarkIcon
                      }}{{
                        isInBookmark(album.collectionCensoredName)
                          ? ""
                          : "-outline"
                      }}
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
                      dark
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
                      <v-btn
                        color="error"
                        icon
                        v-bind="attrs"
                        v-on="on"
                        :href="`https://www.youtube.com/results?search_query=${album.artistName} - ${album.collectionCensoredName}`"
                        target="_blank"
                      >
                        <v-icon> mdi-youtube </v-icon>
                      </v-btn>
                    </template>
                    <span>유튜브 검색</span>
                  </v-tooltip>
                </span>
              </v-card-actions>
            </v-card>

            <!-- Media Panel-->
            <article
              class="media media-wrap"
              v-if="settings.panelType === 'media'"
            >
              <figure class="media-left">
                <p class="image">
                  <img
                    :src="replaceArtworkUrlSize(album.artworkUrl100, '130x130')"
                    :alt="album.collectionCensoredName"
                  />
                </p>
              </figure>
              <div class="media-content">
                <div class="content overflow-content">
                  <div>
                    <strong
                      ><a
                        v-if="album.collectionId"
                        @click="onClickAlbumName(album.collectionId)"
                        >{{ album.collectionCensoredName }}</a
                      ></strong
                    >
                    <br />
                    {{ album.artistName }} (
                    <span class="has-text-grey-light">{{
                      album.primaryGenreName
                    }}</span>
                    )
                  </div>
                </div>
                <div class="level is-mobile">
                  <div class="level-left">
                    <a
                      class="level-item"
                      :href="album.collectionViewUrl"
                      target="_blank"
                    >
                      <v-tooltip
                        type="is-light"
                        label="Download on iTunes"
                        position="is-top"
                        :active="!isMobile"
                      >
                        <i class="fab fa-itunes-note"></i>
                      </v-tooltip>
                    </a>
                    <a class="level-item">
                      <v-tooltip
                        type="is-light"
                        :label="
                          isInBookmark(album.collectionCensoredName)
                            ? 'click to unbookmarked'
                            : 'click to bookmark'
                        "
                        position="is-top"
                        :active="!isMobile"
                      >
                        <i
                          @click="clickBookmarkAlbum(album)"
                          class="fas bookmarkIcon"
                          :class="[
                            {
                              favorite: isInBookmark(
                                album.collectionCensoredName
                              ),
                            },
                            settings.bookmarkIcon,
                          ]"
                        ></i>
                      </v-tooltip>
                    </a>
                    <a
                      v-if="settings.youtubeLink === 'true'"
                      class="level-item"
                      :href="`https://www.youtube.com/results?search_query=${album.artistName} - ${album.collectionCensoredName}`"
                      target="_blank"
                    >
                      <v-tooltip
                        type="is-light"
                        label="search on youtube"
                        position="is-top"
                        :active="!isMobile"
                      >
                        <i class="fab fa-youtube"></i>
                      </v-tooltip>
                    </a>
                  </div>
                </div>
              </div>
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
            :length="Math.floor(this.albums.length / this.settings.perPage) + 1"
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
export default {
  name: "AlbumList",
  data() {
    return {
      current: 1,
      size: "",
      panelIcon: "mdi-grid",
    };
  },
  props: {
    albums: {
      type: Array,
      required: true,
    },
    pageType: {
      type: String,
      required: true,
    },
    isAlbumLoading: {
      type: Boolean,
      required: true,
    },
    searchFailed: {
      type: Boolean,
      required: true,
    },
    bookmarkAlbums: {
      type: Array,
      required: true,
    },
    settings: {
      type: Object,
      required: true,
    },
    isMobile: {
      type: Boolean,
      required: true,
    },
    clickBookmarkAlbum: {
      type: Function,
      required: true,
    },
    isInBookmark: {
      type: Function,
      required: true,
    },
    replaceArtworkUrlSize: {
      type: Function,
      required: true,
    },
  },
  computed: {
    displayedAlbums() {
      return this.paginate(this.albums);
    },
  },
  watch: {
    albums(val, oldVal) {
      if (val !== oldVal) {
        this.current = 1;
      }
    },
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
  },
};
</script>
