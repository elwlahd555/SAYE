<template>
  <nav
    class="navbar is-gradient"
    role="navigation"
    aria-label="main navigation"
  >
    <v-container>
      <v-row>
        <v-col class="d-flex align-self-center" @click="onClickTitle">
          <div class="title white--text font-weight-medium">일반 검색</div>
        </v-col>
        <v-spacer></v-spacer>
        <v-col class="text-right">
          <div>
            <!-- ====================================== 최근 검색 기록 ====================================== -->
            <v-tooltip bottom :active="!isMobile">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  icon
                  x-large
                  dark
                  v-bind="attrs"
                  v-on="on"
                  v-if="recentSearch.length > 0"
                >
                  <v-icon
                    @click="onClickToggleRecentSearchBox"
                    :class="{ 'icon-active': showRecentSearchBox }"
                    >mdi-history</v-icon
                  >

                  <span class="badge" v-if="recentSearch.length > 0">
                    {{ recentSearch.length }}
                  </span>
                </v-btn>
              </template>
              <span> 최근 검색 {{ recentSearch.length }} </span>
            </v-tooltip>

            <!-- ====================================== 북마크 ====================================== -->
            <span>
              <v-tooltip
                bottom
                :active="!isMobile"
                v-if="
                  pageType === 'bookmarks' ||
                  isMobile ||
                  bookmarkAlbums.length === 0
                "
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn icon x-large dark v-bind="attrs" v-on="on">
                    <v-icon
                      @click="onClickShowBookmarks"
                      :class="[
                        { 'icon-active': pageType === 'bookmarks' },
                        settings.bookmarkIcon,
                      ]"
                      >mdi-history</v-icon
                    >

                    <span class="badge" v-if="bookmarkAlbums.length > 0">
                      {{ bookmarkAlbums.length }}
                    </span>
                  </v-btn>
                </template>
                <span> 북마크 {{ bookmarkAlbums.length }}</span>
              </v-tooltip>

              <v-menu v-else open-on-hover bottom>
                <i
                  slot="trigger"
                  @click="onClickShowBookmarks"
                  class="fas fa-2x"
                  :class="[
                    { 'icon-active': pageType === 'bookmarks' },
                    settings.bookmarkIcon,
                  ]"
                ></i>
                <v-list>
                  <v-list-item
                    v-for="(album, index) in latestBookmarkAlbums"
                    :key="index"
                    @click="onClickAlbumName(album.collectionId)"
                  >
                    <article class="media">
                      <figure class="media-left">
                        <p class="image is-64x64">
                          <img :src="album.artworkUrl100" />
                        </p>
                      </figure>
                      <div class="media-content overflow-content">
                        <div class="content">
                          <p>
                            <strong>{{ album.collectionCensoredName }}</strong>
                            <br />
                            {{ album.artistName }}
                          </p>
                        </div>
                      </div>
                    </article>
                  </v-list-item>
                  <v-list-item
                    class="has-text-centered"
                    v-if="bookmarkAlbums.length > 5"
                    @click="onClickShowBookmarks"
                    >View All
                  </v-list-item>
                </v-list>
              </v-menu>
            </span>

            <!-- ====================================== 검색 설정 ====================================== -->
            <v-tooltip bottom :active="!isMobile">
              <template v-slot:activator="{ on, attrs }">
                <v-btn icon x-large dark v-bind="attrs" v-on="on">
                  <v-icon @click="onClickSettings"> mdi-settings </v-icon>
                </v-btn>
              </template>
              <span>검색 설정</span>
            </v-tooltip>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </nav>
</template>

<script>
export default {
  name: "TheNavbar",
  props: {
    showRecentSearchBox: {
      type: Boolean,
      required: true,
    },
    recentSearch: {
      type: Array,
      required: true,
    },
    pageType: {
      type: String,
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
  },
  computed: {
    latestBookmarkAlbums() {
      return this.bookmarkAlbums.slice(0, 5);
    },
  },
  methods: {
    onClickToggleRecentSearchBox() {
      this.$emit("clickToggleRecentSearchBox");
    },
    onClickShowBookmarks() {
      this.$emit("clickShowBookmarks");
    },
    onClickSettings() {
      this.$emit("clickSettings");
    },
    onClickTitle() {
      this.$emit("clickTitle");
    },
    onClickAlbumName(albumId) {
      this.$emit("clickAlbumName", albumId);
    },
  },
};
</script>

<style scoped>
a.dropdown-item {
  padding-right: 1rem;
  border-bottom: 1px solid #ccc;
}
img {
  max-height: 100px !important;
}
.navbar-item {
  cursor: pointer;
}
.badge {
  position: absolute;
  right: -2px;
  top: 4px;
  display: inline-block;
  min-width: 19px;
  padding: 2px 6px;
  font-size: 10px;
  font-weight: bold;
  color: #fff;
  line-height: 1.43;
  vertical-align: text-top;
  white-space: nowrap;
  text-align: center;
  text-shadow: none;
  background-clip: padding-box;
  background-color: #ec5252;
  border: none;
  border-radius: 15px;
  z-index: 1;
}
.nav-icon {
  position: relative;
  margin-left: 10px;
}
.fas {
  color: #ccc;
}
.fas:hover {
  color: #fff;
}
.icon-inactive {
  color: #ccc;
}
.icon-active {
  color: #fff !important;
}
.is-gradient {
  background-color: #008a94 !important;
  background-image: linear-gradient(326deg, #00c8d6, #008a94 94%) !important;
}
.navbar-item {
  color: #fff !important;
}
.navbar-end {
  -webkit-box-pack: end !important;
  -ms-flex-pack: end !important;
  justify-content: flex-end !important;
}
</style>
