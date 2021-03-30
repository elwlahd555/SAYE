<template>
  <v-container pt-5>
    <v-col cols="12">
      <PlaylistTreeview />
    </v-col>

    <v-col cols="12">
      <Bookmark v-show="false" :bookmarkAlbums="bookmarkAlbums" />
    </v-col>

    <v-row>
      <v-col cols="3">
        <h3>Draggable 1</h3>
        <draggable
          class="dragArea list-group"
          :list="list1"
          :group="{ name: 'people', pull: 'clone', put: false }"
          :clone="cloneDog"
          @change="log"
        >
          <div
            class="list-group-item"
            v-for="element in list1"
            :key="element.id"
          >
            {{ element.name }}
          </div>
        </draggable>
      </v-col>

      <v-col cols="3">
        <h3>Draggable 2</h3>
        <draggable
          class="dragArea list-group"
          :list="list2"
          group="people"
          @change="log"
        >
          <div
            class="list-group-item"
            v-for="element in list2"
            :key="element.id"
          >
            {{ element.name }}
          </div>
        </draggable>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import PlaylistTreeview from "@/components/mypage/PlaylistTreeview";
import Bookmark from "@/components/mypage/Bookmark";

import { mapGetters } from "vuex";
import draggable from "vuedraggable";

const albumStore = "albumStore";
let idGlobal = 8;

export default {
  components: {
    PlaylistTreeview,
    Bookmark,
    draggable
  },
  data: () => ({
    list1: [
      { name: "dog 1", id: 1 },
      { name: "dog 2", id: 2 },
      { name: "dog 3", id: 3 },
      { name: "dog 4", id: 4 }
    ],
    list2: [
      { name: "cat 5", id: 5 },
      { name: "cat 6", id: 6 },
      { name: "cat 7", id: 7 }
    ]
  }),
  computed: {
    ...mapGetters(albumStore, {
      bookmarkAlbums: "BOOKMARK_ALBUMS"
    })
  },
  created() {
    this.$store.dispatch(albumStore + "/GET_BOOKMARK_ALBUMS");
  },
  methods: {
    log: function(evt) {
      window.console.log(evt);
    },
    cloneDog({ id }) {
      return {
        id: idGlobal++,
        name: `cat ${id}`
      };
    }
  }
};
</script>
