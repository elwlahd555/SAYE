<template>
  <v-card>
    <v-card-title class="headline indigo lighten-2">
      <span class="white--text headline font-weight-black">
        <span class="black--text">{{ uName }}님</span> 의 Playlist
      </span>
    </v-card-title>
    <v-row dense class="ma-0 pa-0">
      <v-col cols="5">
        <v-list subheader two-line shaped>
          <v-app-bar flat>
            <v-app-bar-title>
              <span class="font-weight-bold">목록 </span>
            </v-app-bar-title>
            <v-spacer></v-spacer>
            <v-btn
              small
              dark
              color="amber lighten-2"
              @click="editMode = !editMode"
            >
              <v-icon>mdi-square-edit-outline</v-icon>
            </v-btn>
            <v-btn
              v-if="editMode"
              small
              dark
              color="green lighten-2"
              @click="onClickPlus"
            >
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </v-app-bar>
          <v-list-item-group v-model="chosen" color="primary">
            <v-list-item v-for="item in myPlaylist" :key="item.pNo">
              <v-icon style="padding-right: 10px"> mdi-playlist-music </v-icon>
              <v-list-item-content @click="onClickPlaylist(item)">
                <v-list-item-title>{{ item.pName }}</v-list-item-title>
              </v-list-item-content>
              <v-btn
                v-if="editMode"
                dark
                small
                color="red lighten-2"
                @click="onClickMinus(item)"
              >
                <v-icon>mdi-minus</v-icon>
              </v-btn>
            </v-list-item>
          </v-list-item-group>
          <v-list-item v-if="newItem">
            <v-text-field
              v-model="inputName"
              label="Playlist 명을 입력하세요"
              filled
              outlined
              class="pt-7"
            ></v-text-field>

            <v-btn icon @click="addPlaylist">
              <v-icon color="success accent-3">mdi-check-bold</v-icon>
            </v-btn>
            <v-btn icon @click="cancel">
              <v-icon color="red accent-3">mdi-close-thick</v-icon>
            </v-btn>
          </v-list-item>
        </v-list>
      </v-col>
      <v-divider vertical></v-divider>

      <v-col cols="7" class="d-flex text-center mt-0 pt-0">
        <div
          v-if="chosen == null"
          class="title grey--text text--lighten-1 font-weight-bold"
          style="align-self: center; margin: 0 auto"
        >
          Select a Playlist
        </div>
        <div
          v-else-if="playlist.length == 0"
          class="title grey--text text--lighten-1 font-weight-bold"
          style="align-self: center; margin: 0 auto"
        >
          Nothing in this playlist
        </div>
        <v-container v-else fluid ma-0 pa-0>
          <v-list class="my-0 py-0" rounded>
            <v-app-bar flat>
              <v-toolbar-title>{{ chosenName }}</v-toolbar-title>
            </v-app-bar>

            <v-list-item-group no-action>
              <draggable
                class="dragArea list-group"
                :list="playlist"
                group="music"
                @change="log"
              >
                <v-list-item v-for="(item, i) in playlist" :key="i">
                  <v-list-item-icon>
                    <v-icon>mdi-music</v-icon>
                  </v-list-item-icon>
                  <v-list-item-avatar>
                    <v-avatar size="56">
                      <img :src="item.mImg" alt="albumImg" />
                    </v-avatar>
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title>{{ item.mTitle }}</v-list-item-title>
                  </v-list-item-content>

                  <v-list-item-action>
                    <v-btn icon @click="handleClick(item)">
                      <v-icon color="red darken-4" class="mr-2">
                        mdi-play
                      </v-icon>
                    </v-btn>
                    <v-btn icon @click="log">
                      <v-icon color="red darken-4" class="mr-2">
                        mdi-information
                      </v-icon>
                    </v-btn>
                  </v-list-item-action>
                </v-list-item>
              </draggable>
            </v-list-item-group>
          </v-list>
        </v-container>
      </v-col>
    </v-row>

    <!-- Playlist Snackbar -->
    <v-snackbar v-model="snackbar" :timeout="2000" :color="snackbarColor">
      {{ snackbarText }}
      <template v-slot:action="{ attrs }">
        <v-btn dark text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-card>
</template>

<script>
// import Swal from "sweetalert2";
import axios from "axios";
import Swal from "sweetalert2";
import getYouTubeID from "get-youtube-id";
import draggable from "vuedraggable";
import { mapState } from "vuex";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  components: {
    draggable
  },
  data: () => ({
    selectedItem1: 1,
    chosen: [],
    chosenName: "",
    playlist: [],
    playlistNo: "",
    inputName: "",
    myPlaylist: [],
    newItem: false,
    editMode: false,
    snackbar: false,
    snackbarColor: "",
    snackbarText: ""
  }),
  computed: {
    ...mapState({ uName: "uName", uId: "uId", asidePlaylist: "asidePlaylist" })
  },
  mounted() {
    axios.get(`${spring_URL}/playlist?uNo=${this.uId}`).then(list => {
      this.myPlaylist = list.data;
    });
  },
  methods: {
    onClickPlaylist(playlist) {
      this.playlistNo = playlist.pNo;
      axios.get(`${spring_URL}/?pNo=${playlist.pNo}`).then(res => {
        this.playlist = res.data;
        this.chosenName = playlist.pName;
      });
    },
    cancel() {
      this.inputName = "";
      this.newItem = false;
    },
    addPlaylist() {
      axios
        .post(
          `${spring_URL}/playlist/add?pName=${this.inputName}&pUNo=${this.uId}`
        )
        .then(res => {
          if (res.data == "플레이리스트 추가 성공") {
            axios.get(`${spring_URL}/playlist?uNo=${this.uId}`).then(list => {
              this.myPlaylist = list.data;
              this.snackbar = true;
              this.snackbarColor = "success";
              this.snackbarText = `${this.inputName} 추가`;
              this.inputName = "";
            });

            this.newItem = false;
          }
        });
    },
    onClickMinus(music) {
      axios
        .delete(`${spring_URL}/playlist/delete?pNo=${music.pNo}`)
        .then(res => {
          if (res.data == "플레이리스트 삭제 성공") {
            axios.get(`${spring_URL}/playlist?uNo=${this.uId}`).then(list => {
              this.myPlaylist = list.data;
            });
            this.snackbar = true;
            this.snackbarColor = "error";
            this.snackbarText = `${music.pName} 제거`;
          }
        });
    },
    onClickPlus() {
      if (!this.newItem) {
        this.newItem = true;
      } else {
        Swal.fire({
          icon: "warning",
          title: "잠깐!",
          text: "체크 버튼 혹은 취소 버튼을 눌러주세요ㅠㅅㅠ",
          confirmButtonColor: "#f8bb86"
        });
      }
    },
    handleClick(music) {
      this.videoId = getYouTubeID(music.mUrl);
      this.$store.dispatch("setVideoId", this.videoId);

      this.$store.dispatch("setPlayMusic", music);
    },
    log(evt) {
      if (evt.added) {
        const newId = evt.added.element.mId;
        const newMNo = evt.added.element.mNo;
        let cnt = 0;
        for (let { mId } of this.playlist) {
          if (newId == mId) {
            cnt++;
          }
        }
        if (cnt == 2) {
          console.log("duplicate");
          const idx = this.playlist.findIndex(item => {
            return item.mId === newId;
          });
          console.log(idx);
          this.playlist.splice(idx, 1);
        } else {
          console.log("axios good to go");
          axios
            .post(`${spring_URL}/add?mNo=${newMNo}&pNo=${this.playlistNo}`)
            .then(res => {
              if (res.data == "플레이리스트 내 곡 추가 성공") {
                this.snackbar = true;
                this.snackbarColor = "success";
                this.snackbarText = `${evt.added.element.mTitle} 추가`;
              }
            });
        }
      }
    }
  }
};
</script>
