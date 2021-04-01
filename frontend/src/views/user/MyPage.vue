<template>
  <v-container pt-5>
    <v-col cols="12">
      <PlaylistTreeview />
    </v-col>

    <v-col cols="12">
      <Bookmark :bookmarkAlbums="bookmarkAlbums" />
    </v-col>

    <v-container>
      <v-row>
        <v-col cols="6">
          <v-card class="mx-auto" tile>
            <v-list shaped>
              <v-subheader class="deep-purple--text headline"
                >Bookmark</v-subheader
              >
              <v-list-item-group v-model="selectedItem1" color="primary">
                <draggable
                  class="dragArea"
                  :list="bookmarkAlbums"
                  :group="{ name: 'music', pull: 'clone', put: false }"
                  @change="log"
                >
                  <v-list-item v-for="item in bookmarkAlbums" :key="item.mId">
                    <v-list-item-icon>
                      <v-icon>mdi-music</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                      <v-list-item-title
                        v-text="item.mTitle"
                      ></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </draggable>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-col>

        <v-col cols="6">
          <v-card class="mx-auto" tile>
            <v-list shaped>
              <v-subheader>Playlist</v-subheader>
              <v-list-item-group v-model="selectedItem2" color="primary">
                <draggable
                  class="dragArea list-group"
                  :list="list2"
                  group="music"
                  @change="log"
                >
                  <v-list-item v-for="(item, i) in list2" :key="i">
                    <v-list-item-icon>
                      <v-icon>mdi-music</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                      <v-list-item-title
                        v-text="item.mTitle"
                      ></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </draggable>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import PlaylistTreeview from "@/components/mypage/PlaylistTreeview";
import Bookmark from "@/components/mypage/Bookmark";

import { mapGetters } from "vuex";
import draggable from "vuedraggable";

const albumStore = "albumStore";

export default {
  components: {
    PlaylistTreeview,
    Bookmark,
    draggable
  },
  computed: {
    ...mapGetters(albumStore, {
      bookmarkAlbums: "BOOKMARK_ALBUMS"
    })
  },
  created() {
    this.$store.dispatch(albumStore + "/GET_BOOKMARK_ALBUMS");
  },
  data: () => ({
    selectedItem1: 1,
    selectedItem2: 1,
    list1: [],
    list2: [
      {
        mAlbum: "Laundry",
        mAlbumId: "441gmBDpIoZTSaWVmm6wl8",
        mArtist: "Lee Juck",
        mArtistId: "0pfVDTchnVMBTzGylIuJNa",
        mCnt: 0,
        mDate: "2010-09-10",
        mEmotion: null,
        mGenre: "k-indie,korean pop",
        mId: "2Y4vHanq3lXlkZijixEwXf",
        mImg:
          "https://i.scdn.co/image/ab67616d0000b273ae5d4b7a7c64cbf6bc733fbd",
        mNo: 780,
        mPopularity: "12",
        mPreview:
          "https://p.scdn.co/mp3-preview/a18cf6cf874436766a099146a5d02aaf3ef59e1c?cid=d3334c83c5164e88be121b0c08f6a9c8",
        mTitle: "Laundry",
        mUrl: "https://www.youtube.com/watch?v=7bQ_GnMG8Fg"
      }
    ]
  }),
  methods: {
    log: function(evt) {
      window.console.log(evt);
    }
  }
};
</script>
