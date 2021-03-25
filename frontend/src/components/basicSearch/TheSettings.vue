<template>
  <v-card>
    <v-toolbar>
      <v-card-title>Settings</v-card-title>
    </v-toolbar>
    <v-container>
      <v-row>
        <v-col cols="6">
          <label>Initial Search Query</label>
        </v-col>
        <v-col cols="6">
          <v-text-field
            filled
            dense
            v-model="settings.initialSearchQuery"
          ></v-text-field>
        </v-col>
        <v-col cols="6">
          <label>Panel Type</label>
        </v-col>
        <v-col cols="6">
          <v-radio-group v-model="settings.panelType" row mandatory>
            <v-radio label="card" color="indigo">
              <v-icon>mdi-dots-grid</v-icon>
              <span>Card</span>
            </v-radio>
            <v-radio label="media" color="indigo">
              <v-icon>mdi-view-list</v-icon>
              <span>Media</span>
            </v-radio>
          </v-radio-group>
        </v-col>
        <v-col cols="6">
          <label>Bookmark Icon</label>
        </v-col>
        <v-col cols="6">
          <v-radio-group v-model="settings.bookmarkIcon" row mandatory>
            <v-radio label="heart" append-icon="mdi-heart">
              <v-icon>mdi-heart</v-icon>
              <span>Heart</span>
            </v-radio>
            <v-radio label="star">
              <v-icon>mdi-star</v-icon>
              <span>Star</span>
            </v-radio>
          </v-radio-group>
        </v-col>
        <v-col cols="6">
          <label>Search on youtube icon</label>
        </v-col>
        <v-col cols="6">
          <v-radio-group v-model="settings.youtubeLink" row mandatory>
            <v-radio label="Show" color="red darken-2">
              <v-icon>mdi-eye-outline</v-icon>
              <span>Show</span>
            </v-radio>
            <v-radio label="Hide" color="red darken-2">
              <v-icon>mdi-eye-off-outline</v-icon>
              <span>Hide</span>
            </v-radio>
          </v-radio-group>
        </v-col>
        <v-col cols="6">
          <label>Search Results Per Page</label>
        </v-col>
        <v-col cols="6">
          <v-radio-group v-model="settings.perPage" row mandatory>
            <v-radio label="20" color="lime">
              <span>20</span>
            </v-radio>
            <v-radio label="40" color="lime">
              <span>40</span>
            </v-radio>
            <v-radio label="60" color="lime">
              <span>60</span>
            </v-radio>
          </v-radio-group>
        </v-col>
      </v-row>

      <v-btn color="warning" @click="$emit('close')"> close </v-btn>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "TheSettings",
  props: {
    settings: {
      type: Object,
      required: true
    }
  },
  watch: {
    "settings.panelType"(settingValue) {
      this.onClickUpdateSettings("panelType", settingValue);
    },
    "settings.bookmarkIcon"(settingValue) {
      this.onClickUpdateSettings("bookmarkIcon", settingValue);
    },
    "settings.youtubeLink"(settingValue) {
      this.onClickUpdateSettings("youtubeLink", settingValue);
    },
    "settings.perPage"(settingValue) {
      this.onClickUpdateSettings("perPage", settingValue);
    },
    "settings.initialSearchQuery"(settingValue) {
      this.onClickUpdateSettings("initialSearchQuery", settingValue);
    }
  },
  methods: {
    onClickUpdateSettings(settingName, settingValue) {
      this.$emit("clickUpdateSettings", settingName, settingValue);
    }
  }
};
</script>

<style></style>
