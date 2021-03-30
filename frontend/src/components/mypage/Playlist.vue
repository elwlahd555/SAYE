<template>
  <v-container fluid>
    <div class="white--text indigo darken-4">
      {{ playlist.name }}
    </div>

    <v-virtual-scroll :items="items" :item-height="50" height="500">
      <template v-slot:default="{ item }">
        <v-list-item>
          <v-list-item-avatar>
            <v-avatar :color="item.color" size="56" class="white--text">
              {{ item.initials }}
            </v-avatar>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ item.fullName }}</v-list-item-title>
          </v-list-item-content>

          <v-list-item-action>
            <v-btn icon @click="alert('click')">
              <v-icon color="red darken-4" class="mr-2">
                mdi-play
              </v-icon>
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
  data: () => ({
    colors: ["#2196F3", "#90CAF9", "#64B5F6"],
    names: ["Oliver", "Jake", "Noah"],
    surnames: ["Smith", "Anderson", "Clark"]
  }),

  computed: {
    items() {
      const namesLength = this.names.length;
      const surnamesLength = this.surnames.length;
      const colorsLength = this.colors.length;

      return Array.from({ length: 15 }, () => {
        const name = this.names[this.genRandomIndex(namesLength)];
        const surname = this.surnames[this.genRandomIndex(surnamesLength)];

        return {
          color: this.colors[this.genRandomIndex(colorsLength)],
          fullName: `${name} ${surname}`,
          initials: `${name[0]} ${surname[0]}`
        };
      });
    }
  },

  methods: {
    genRandomIndex(length) {
      return Math.ceil(Math.random() * (length - 1));
    }
  }
};
</script>
