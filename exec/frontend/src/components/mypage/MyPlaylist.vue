<template>
  <v-card>
    <v-app-bar class=" indigo lighten-2">
      <v-card-title class="headline font-weight-black">
        Playlist에 추가
      </v-card-title>
      <v-spacer></v-spacer>

      <v-btn small color="success lighten-2" @click="onClickPlus">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </v-app-bar>
    <v-list subheader two-line flat>
      <v-subheader class="indigo--text">{{ uName }}님의 Playlist</v-subheader>

      <v-list-item-group v-model="chosen" multiple>
        <v-list-item v-for="item in myPlaylist" :key="item.pNo">
          <v-list-item-action>
            <v-checkbox
              v-model="active"
              :value="item.pNo"
              color="primary"
            ></v-checkbox>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title>{{ item.pName }}</v-list-item-title>
          </v-list-item-content>
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
    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="primary" text @click="addMusicToPlaylist">
        Apply
      </v-btn>
      <v-btn color="error" text @click="closeDialog">
        Cancel
      </v-btn>
    </v-card-actions>
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
import Swal from "sweetalert2";
import axios from "axios";
import { mapState } from "vuex";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  props: {
    myPlaylist: {
      type: Array,
      require: true
    },
    selected: {
      type: Object,
      require: true
    }
  },
  data: () => ({
    playlist: [],
    chosen: [],
    inputName: "",
    newItem: false,
    active: [],
    snackbar: false,
    snackbarText: "",
    snackbarColor: ""
  }),
  computed: {
    ...mapState({ uId: "uId", uName: "uName" }),
    idxOfpNo() {
      return this.myPlaylist.map(item => item.pNo);
    }
  },
  mounted() {
    axios
      .get(
        `${spring_URL}/playlist/belong?mNo=${this.selected.mNo}&uNo=${this.uId}`
      )
      .then(res => {
        for (let data of res.data) {
          if (this.idxOfpNo.includes(data.pNo)) {
            this.active.push(data.pNo);
          }
        }
        console.log(this.active);
      });
  },
  watch: {
    selected() {
      this.active = [];
      axios
        .get(
          `${spring_URL}/playlist/belong?mNo=${this.selected.mNo}&uNo=${this.uId}`
        )
        .then(res => {
          for (let data of res.data) {
            if (this.idxOfpNo.includes(data.pNo)) {
              this.active.push(data.pNo);
            }
          }
          console.log(this.active);
        });
    }
  },
  methods: {
    closeDialog() {
      this.$emit("close");
      this.chosen = [];
      this.newItem = false;
    },
    addMusicToPlaylist() {
      for (let i of this.chosen) {
        console.log(
          `${spring_URL}/add?mNo=${this.selected.mNo}&pNo=${this.idxOfpNo[i]}`
        );
        axios
          .post(
            `${spring_URL}/add?mNo=${this.selected.mNo}&pNo=${this.idxOfpNo[i]}`
          )
          .then(res => {
            if (res.data == "플레이리스트 내 곡 추가 성공") {
              this.snackbar = true;
              this.snackbarColor = "success";
              this.snackbarText = `플레이리스트에 ${this.selected.mTitle} 추가 성공`;
            }
          })
          .catch(err => {
            console.log(err);
            Swal.fire({
              icon: "error",
              title: "이미 플레이리스트에 있음",
              text: `${this.selected.mNo} already in playlist ${this.dixOfpNo[i]}`,
              confirmButtonColor: "#f27474"
            });
          });
      }
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
            this.myPlaylist.push({ pName: this.inputName });
            this.inputName = "";
            this.newItem = false;
          }
        });
    },
    onClickPlus() {
      if (!this.newItem) {
        this.newItem = true;
      } else {
        alert("이미 클릭함");
      }
    }
  }
};
</script>

<style></style>
