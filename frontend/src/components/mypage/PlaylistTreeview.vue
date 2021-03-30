<template>
  <v-card>
    <v-card-title class="indigo white--text headline">
      <v-icon dark style="padding-right: 10px">
        mdi-playlist-music
      </v-icon>
      My Playlist
    </v-card-title>
    <v-row class="pa-4" justify="space-between">
      <v-col cols="5">
        <v-treeview
          :active.sync="active"
          :items="items"
          :load-children="fetchPlaylists"
          :open.sync="open"
          activatable
          color="warning"
          open-all
          transition
        >
          <template v-slot:prepend="{ item }">
            <v-icon v-if="!item.children">
              mdi-music-circle
            </v-icon>
          </template>
        </v-treeview>
      </v-col>

      <v-divider vertical></v-divider>

      <v-col class="d-flex text-center">
        <v-scroll-y-transition mode="out-in">
          <div
            v-if="!selected"
            class="title grey--text text--lighten-1 font-weight-light"
            style="align-self: center;"
          >
            Select a Playlist
          </div>
          <Playlist v-else :key="selected.id" :playlist="selected" />
        </v-scroll-y-transition>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

import Playlist from "@/components/mypage/Playlist";

const spring_URL = process.env.VUE_APP_SPRING_URL;
const pause = ms => new Promise(resolve => setTimeout(resolve, ms));

export default {
  components: {
    Playlist
  },
  data: () => ({
    active: [],
    avatar: null,
    open: [],
    playlist: []
  }),
  computed: {
    ...mapState({ uId: "uId" }),
    items() {
      return [
        {
          name: this.uId + "님의 플레이리스트",
          children: this.playlist
        }
      ];
    },
    selected() {
      if (!this.active.length) return undefined;

      const id = this.active[0];

      return this.users.find(user => user.id === id);
    }
  },
  methods: {
    async fetchPlaylists(item) {
      // Remove in 6 months and say
      // you've made optimizations! :)
      await pause(1000);

      return axios
        .get(`${spring_URL}/playlist?uNo=1`)
        .then(res => {
          item.children.push(...res.data);
        })
        .catch(err => console.warn(err));
    }
  }
};
</script>
