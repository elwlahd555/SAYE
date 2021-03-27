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
            <v-radio label="card" value="card" color="indigo">
              <v-icon>mdi-dots-grid</v-icon>
              <span>Card</span>
            </v-radio>
            <v-radio label="media" value="media" color="indigo">
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
            <v-radio label="heart" value="mdi-heart" on-icon="mdi-heart">
              <v-icon>mdi-heart</v-icon>
              <span>Heart</span>
            </v-radio>
            <v-radio label="star" value="mdi-star" on-icon="mdi-star">
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
            <v-radio label="Show" value="true" color="red darken-2">
              <v-icon>mdi-eye-outline</v-icon>
              <span>Show</span>
            </v-radio>
            <v-radio label="Hide" value="false" color="red darken-2">
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
            <v-radio label="20" value="20" color="lime">
              <span>20</span>
            </v-radio>
            <v-radio label="40" value="40" color="lime">
              <span>40</span>
            </v-radio>
            <v-radio label="60" value="60" color="lime">
              <span>60</span>
            </v-radio>
          </v-radio-group>
        </v-col>
      </v-row>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="warning" @click="closeDialog"> close </v-btn>
      </v-card-actions>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "TheSettings",
  props: {
    settings: {
      type: Object,
      required: true,
    },
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
      console.log(this.$store.state.albumStore.settings.perPage);
    },
    "settings.initialSearchQuery"(settingValue) {
      this.onClickUpdateSettings("initialSearchQuery", settingValue);
    },
  },
  methods: {
    onClickUpdateSettings(settingName, settingValue) {
      this.$emit("clickUpdateSettings", settingName, settingValue);
    },
    closeDialog() {
      console.log("hi");
      this.$emit("dialogClosed");
    },
  },
};
</script>

<style></style>
