<template>
  <v-card>
    <v-toolbar color="#008a94" dark>
      <v-card-title>Settings</v-card-title>
    </v-toolbar>
    <v-container>
      <v-row class="mt-2">
        <v-col cols="6">
          <div class="title font-weight-black text-center">
            Initial Search Query
          </div>
        </v-col>
        <v-col cols="6">
          <v-text-field
            dense
            solo-inverted
            v-model="settings.initialSearchQuery"
          ></v-text-field>
        </v-col>
        <v-col cols="6">
          <div class="title font-weight-black text-center">Panel Type</div>
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
          <div class="title font-weight-black text-center">Bookmark Icon</div>
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
        <!-- <v-col cols="6">
          <div class="title font-weight-black text-center">
            Search on youtube icon
          </div>
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
        </v-col> -->
        <v-col cols="6">
          <div class="title font-weight-black text-center">
            Results Per Page
          </div>
        </v-col>
        <v-col cols="6">
          <v-radio-group v-model="settings.perPage" row mandatory>
            <v-radio label="8" value="8" color="lime">
              <span>8</span>
            </v-radio>
            <v-radio label="16" value="16" color="lime">
              <span>16</span>
            </v-radio>
            <v-radio label="32" value="32" color="lime">
              <span>32</span>
            </v-radio>
          </v-radio-group>
        </v-col>
      </v-row>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="error" text @click="closeDialog"> close </v-btn>
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
      console.log(this.$store.state.albumStore.settings.perPage);
    },
    "settings.initialSearchQuery"(settingValue) {
      this.onClickUpdateSettings("initialSearchQuery", settingValue);
    }
  },
  methods: {
    onClickUpdateSettings(settingName, settingValue) {
      this.$emit("clickUpdateSettings", settingName, settingValue);
    },
    closeDialog() {
      console.log("hi");
      this.$emit("dialogClosed");
    }
  }
};
</script>

<style></style>
