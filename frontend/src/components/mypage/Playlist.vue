<template>
  <v-container fluid>
    <v-virtual-scroll :items="playlist" :item-height="50" height="250">
      <template v-slot:default="{ item }">
        <v-list-item>
          <v-list-item-avatar>
            <v-avatar size="56">
              <img :src="item.mImg" alt="albumImg" />
            </v-avatar>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ item.mTitle }}</v-list-item-title>
          </v-list-item-content>

          <v-list-item-action>
            <v-btn icon @click="alert('click')">
              <v-icon color="red darken-4" class="mr-2"> mdi-play </v-icon>
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </template>
    </v-virtual-scroll>
  </v-container>
</template>

<script>
export default {
  props: ["playlist"],
  data: () => ({}),
  computed: {},

  methods: {
    genRandomIndex(length) {
      return Math.ceil(Math.random() * (length - 1));
    },
  },
};
</script>
