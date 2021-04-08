<template>
  <section class="section">
    <v-container mt-0>
      <v-row v-if="!isAlbumLoading && albums.length > 0">
        <v-col cols="1" class="text-right">
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-icon @click="onClickUpdateSettings" v-bind="attrs" v-on="on">{{
                panelIcon
              }}</v-icon>
            </template>
            <span>switch panel view</span>
          </v-tooltip>
        </v-col>
        <v-col cols="10">
          <span
            class="is-size-5-desktop is-size-6-mobile has-text-grey"
            v-if="pageType !== 'bookmarks'"
          >
            Recommendation Results
          </span>
          <span class="is-size-5-desktop is-size-6-mobile has-text-grey" v-else>
            Bookmarks</span
          >
        </v-col>
      </v-row>
      <!-- Album List -->
      <transition name="list" mode="out-in">
        <v-row v-if="!isAlbumLoading && albums.length > 0" :key="pageType">
          <v-col v-for="album in albums" :key="album.m_id" :cols="colSize">
            <!-- Card Panel  -->
            <v-card v-if="settings.panelType === 'card'">
              <v-img
                :src="album.m_img"
                lazy-src="https://picsum.photos/id/11/100/60"
                :alt="album.m_img"
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
                <a v-if="album.m_id" @click="handleClick(album)">
                  <span
                    class="d-inline-block text-truncate"
                    style="max-width: 200px"
                    >{{ album.m_title }}</span
                  >
                </a>
              </v-card-title>
              <v-card-subtitle class="py-0">
                <span
                  class="d-inline-block text-truncate "
                  style="max-width: 200px"
                >
                  {{ album.m_album }}
                </span>
              </v-card-subtitle>
              <v-card-subtitle class="py-0">
                <span
                  class="d-inline-block text-truncate"
                  style="max-width: 200px"
                >
                  {{ album.m_artist }}
                </span>
              </v-card-subtitle>
              <v-card-text>
                <span
                  class="d-inline-block text-truncate"
                  style="max-width: 200px"
                >
                  {{ album.m_genre }}
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
                      }}{{ isInBookmark(album.m_id) ? '' : '-outline' }}
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
                      @click="onClickMyPlaylist(album)"
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
                    :src="album.m_img"
                    :alt="album.m_album"
                    style="border-radius: 50%; 
                            max-height: 100px;
                            max-width: 100px;"
                  />

                  <v-list-item-content class="ml-12">
                    <v-list-item-title>{{ album.m_title }}</v-list-item-title>

                    <v-list-item-subtitle>
                      {{ album.m_album }}
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
                            }}{{ isInBookmark(album.m_id) ? '' : '-outline' }}
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
                            @click="playlistDialog = true"
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
      <!-- <v-row v-if="!isAlbumLoading && albums.length > 0">
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
      </v-row> -->
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

    <!-- Playlist Dialog -->
    <v-dialog v-model="playlistDialog" persistent max-width="700">
      <MyPlaylist
        :myPlaylist="myPlaylist"
        :selected="selected"
        @close="playlistDialog = false"
      />
    </v-dialog>
  </section>
</template>

<script>
import getYouTubeID from 'get-youtube-id';

import MyPlaylist from '@/components/mypage/MyPlaylist';
import axios from 'axios';

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: 'AlbumList',
  components: {
    MyPlaylist,
  },
  data() {
    return {
      current: 1,
      size: '',
      panelIcon: 'mdi-grid',
      playlistDialog: false,
      selected: [],
      myPlaylist: [],
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
  },
  computed: {
    colSize() {
      return this.settings.panelType === 'card' ? '3' : '12';
    },
    uNo() {
      return this.$store.state.uId;
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
    onClickUpdateSettings() {
      const settingValue =
        this.settings.panelType === 'card' ? 'media' : 'card';
      this.panelIcon =
        this.panelIcon === 'mdi-grid' ? 'mdi-format-list-text' : 'mdi-grid';
      this.$emit('clickUpdateSettings', 'panelType', settingValue);
    },
    onClickMyPlaylist(music) {
      const musicSend = {
        mNo: music.m_no,
        mTitle: music.m_title,
        mGenre: music.m_genre,
        mArtist: music.m_artist,
        mAlbum: music.m_album,
        mPreview: music.m_preview,
        mImg: music.m_img,
        mPopularity: music.m_popularity,
        mId: music.m_id,
        mArtistId: music.m_artist_id,
        mAlbumId: music.m_album_id,
        mCnt: music.m_cnt,
        mEmotion: music.m_emotion,
        mDate: music.m_date,
        mUrl: music.m_url,
      };
      axios.get(`${spring_URL}/playlist?uNo=${this.uNo}`).then((list) => {
        this.myPlaylist = list.data;
        this.selected = musicSend;
        this.playlistDialog = true;
      });
    },
    handleClick(music) {
      const musicSend = {
        mNo: music.m_no,
        mTitle: music.m_title,
        mGenre: music.m_genre,
        mArtist: music.m_artist,
        mAlbum: music.m_album,
        mPreview: music.m_preview,
        mImg: music.m_img,
        mPopularity: music.m_popularity,
        mId: music.m_id,
        mArtistId: music.m_artist_id,
        mAlbumId: music.m_album_id,
        mCnt: music.m_cnt,
        mEmotion: music.m_emotion,
        mDate: music.m_date,
        mUrl: music.m_url,
      };

      let videoId = getYouTubeID(music.m_url);
      // console.log(music);
      if (!videoId) {
        // console.log('nothing...');
        const url = `https://www.youtube.com/results?search_query=${music.m_artist} - ${music.m_title}`;
        const strWindowFeatures =
          'location=yes,height=800,width=600,scrollbars=yes,status=yes';
        window.open(url, '_blank', strWindowFeatures);
      } else {
        this.$store.dispatch('setVideoId', videoId);
        this.$store.dispatch('setPlayMusic', musicSend);
        this.$store.dispatch('addToPlaylist', musicSend);
      }
    },
  },
};
</script>
