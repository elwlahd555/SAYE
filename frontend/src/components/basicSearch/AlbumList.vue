<template>
  <v-container>
    <v-container mt-0>
      <v-row v-if="!isAlbumLoading && albums.length > 0">
        <v-col cols="6">
          <span class="text--grey" v-if="pageType !== 'bookmarks'">
            Search Results
          </span>
          <span class="text--grey" v-else> Bookmarks</span>
        </v-col>
        <v-col cols="5" class="text-right">
          <span class="text--grey"> {{ albums.length }} album(s) </span>
        </v-col>
        <v-col cols="1" class="text-left">
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on" @click="onClickUpdateSettings">
                mdi-dots-grid
              </v-icon>
            </template>
            <span>Switch panel view</span>
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
            :class="[
              settings.panelType === 'card'
                ? 'is-3-widescreen is-3-desktop is-4-tablet'
                : 'is-4-widescreen  is-4-desktop is-6-tablet is-12-mobile'
            ]"
            v-for="album in displayedAlbums"
            :key="album.collectionId"
          >
            <!-- Card Panel  -->
            <v-card v-if="settings.panelType === 'card'">
              <v-img
                :src="replaceArtworkUrlSize(album.artworkUrl100, '300x250')"
                :alt="album.collectionCensoredName"
              />
              <div class="card-content">
                <div class="media">
                  <div class="media-content overflow-content">
                    <div
                      class="title is-size-6-widescreen is-size-6-desktop album-name"
                    >
                      <a
                        v-if="album.collectionId"
                        @click="onClickAlbumName(album.collectionId)"
                        >{{ album.collectionCensoredName }}</a
                      >
                    </div>
                    <div class="subtitle is-6">
                      {{ album.artistName }} <br />
                      <span class="has-text-grey-light">{{
                        album.primaryGenreName
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <footer class="card-footer">
                <a
                  :href="album.collectionViewUrl"
                  target="_blank"
                  class="card-footer-item"
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
                <span class="heart card-footer-item">
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
                      class="fas fa-lg bookmarkIcon"
                      :class="[
                        {
                          favorite: isInBookmark(album.collectionCensoredName)
                        },
                        settings.bookmarkIcon
                      ]"
                    ></i>
                  </v-tooltip>
                </span>
                <a
                  v-if="settings.youtubeLink === 'true'"
                  :href="
                    `https://www.youtube.com/results?search_query=${album.artistName} - ${album.collectionCensoredName}`
                  "
                  target="_blank"
                  class="card-footer-item"
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
              </footer>
            </v-card>
            <!-- Media Panel-->
            <article
              class="media media-wrap"
              v-if="settings.panelType === 'media'"
            >
              <figure class="media-left">
                <p class="image ">
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
                              )
                            },
                            settings.bookmarkIcon
                          ]"
                        ></i>
                      </v-tooltip>
                    </a>
                    <a
                      v-if="settings.youtubeLink === 'true'"
                      class="level-item"
                      :href="
                        `https://www.youtube.com/results?search_query=${album.artistName} - ${album.collectionCensoredName}`
                      "
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
      <div class="columns is-mobile" v-if="isAlbumLoading">
        <div class="column loading">
          <v-progress-circular
            :is-full-page="false"
            :active.sync="isAlbumLoading"
            :can-cancel="false"
            indeterminate
          ></v-progress-circular>
        </div>
      </div>
      <!-- Pagination -->
      <div
        class="columns is-multiline is-mobile"
        v-if="!isAlbumLoading && albums.length > 0"
      >
        <div class="column is-12" v-if="albums.length > 0">
          <hr />
          <v-pagination
            :total="albums.length"
            :current.sync="current"
            :order="order"
            :size="size"
            :simple="isSimple"
            :rounded="isRounded"
            :per-page="settings.perPage"
          >
          </v-pagination>
        </div>
      </div>
      <!-- No Bookmark message-->
      <template v-if="pageType === 'bookmarks' && albums.length === 0">
        <div class="columns is-multiline is-mobile">
          <div class="column">
            <h3 class="title is-4 has-text-centered">
              You have no saved bookmarks.
            </h3>
          </div>
        </div>
      </template>
      <!-- Search results message -->
      <template v-if="searchFailed && !isAlbumLoading">
        <div class="columns is-multiline is-mobile">
          <div class="column">
            <h3 class="title is-4 has-text-centered">Nothing found.</h3>
            <h3 class="title is-4 has-text-centered">Please Search Again!</h3>
          </div>
        </div>
      </template>
    </v-container>
  </v-container>
</template>

<script>
export default {
  name: "AlbumList",
  data() {
    return {
      current: 1,
      order: "is-centered",
      size: "",
      isSimple: false,
      isRounded: false
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
    },
    replaceArtworkUrlSize: {
      type: Function,
      required: true
    }
  },
  computed: {
    displayedAlbums() {
      return this.paginate(this.albums);
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
      this.$emit("clickUpdateSettings", "panelType", settingValue);
    },
    onClickAlbumName(albumId) {
      console.log("clicked");
      this.$emit("clickAlbumName", albumId);
      console.log(albumId);
    }
  }
};
</script>
