<template>
  <v-card>
    <v-card-title class="headline pink lighten-2">
      <span class="white--text headline font-weight-black">
        Bookmark
      </span>
    </v-card-title>
    <v-list-item-group v-model="selectedItem1" color="primary">
      <draggable
        class="dragArea"
        :list="bookmarkAlbums"
        :group="{ name: 'music', pull: 'clone', put: false }"
        :clone="cloneMusic"
      >
        <v-list-item v-for="item in bookmarkAlbums" :key="item.mId">
          <v-list-item-icon>
            <v-icon>mdi-music</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title> {{ item.mTitle }}</v-list-item-title>
            <v-list-item-subtitle>{{ item.mAlbum }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </draggable>
    </v-list-item-group>
  </v-card>
</template>

<script>
import draggable from "vuedraggable";

export default {
  components: {
    draggable
  },
  props: {
    bookmarkAlbums: {
      type: Array,
      required: true
    }
  },
  data: () => ({
    selectedItem1: 1
  }),
  methods: {
    cloneMusic(music) {
      return music;
    }
  }
};
</script>
