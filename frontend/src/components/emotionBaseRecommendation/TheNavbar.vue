<template>
  <nav
    class="navbar is-gradient"
    role="navigation"
    aria-label="main navigation"
  >
    <v-container>
      <v-row>
        <v-col
          style="cursor: pointer"
          class="d-flex align-self-center"
          @click="onClickTitle"
        >
          <div class="headline white--text font-weight-black">감정 기반 음악 추천</div>
        </v-col>
        <v-spacer></v-spacer>
        <v-col class="text-right">
          <div>
            <!-- ====================================== 북마크 ====================================== -->
            <span>
              <v-tooltip
                bottom
                :active="!isMobile"
                v-if="pageType === 'bookmarks' || bookmarkAlbums.length === 0"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn icon x-large dark v-bind="attrs" v-on="on">
                    <v-icon
                      @click="onClickShowBookmarks"
                      color="pink lighten-4"
                    >
                      {{ settings.bookmarkIcon }}
                    </v-icon>
                  </v-btn>
                </template>
                <span> 북마크 {{ bookmarkAlbums.length }}</span>
              </v-tooltip>

              <v-menu v-else open-on-hover left :nudge-width="150">
                <template v-slot:activator="{ on: menu, attrs }">
                  <v-tooltip top>
                    <template v-slot:activator="{ on: tooltip }">
                      <v-btn
                        icon
                        x-large
                        dark
                        v-bind="attrs"
                        v-on="{ ...tooltip, ...menu }"
                      >
                        <v-icon
                          @click="onClickShowBookmarks"
                          color="pink lighten-4"
                        >
                          {{ settings.bookmarkIcon }}
                        </v-icon>
                        <span class="badge" v-if="bookmarkAlbums.length > 0">
                          {{ bookmarkAlbums.length }}
                        </span>
                      </v-btn>
                    </template>
                    <span>북마크 {{ bookmarkAlbums.length }}</span>
                  </v-tooltip>
                </template>
                <v-list>
                  <v-list-item
                    v-for="(album, index) in latestBookmarkAlbums"
                    :key="index"
                    @click="onClickAlbumName(album.mId)"
                  >
                    <v-list-item-avatar tile size="80">
                      <img :src="album.mImg" />
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <div class="overline mb-4">
                        {{ album.mArtist }}
                      </div>

                      <v-list-item-title>
                        <span
                          class="d-inline-block text-truncate"
                          style="max-width: 80px"
                          >{{ album.mTitle }}</span
                        >
                      </v-list-item-title>
                    </v-list-item-content>
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
      required: true
    },
    recentSearch: {
      type: Array,
      required: true
    },
    pageType: {
      type: String,
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
    }
  },
  computed: {
    latestBookmarkAlbums() {
      return this.bookmarkAlbums.slice(0, 5);
    }
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
    }
  }
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

.is-gradient {
  background-color: #141212 !important;
}
</style>
