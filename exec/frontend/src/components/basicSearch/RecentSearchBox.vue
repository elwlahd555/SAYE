<template>
  <section>
    <div class="container">
      <div class="columns is-centered">
        <div class="column" :class="searchBoxResize">
          <div class="tag-wrap">
            <div class="title block is-6 has-text-grey-light has-text-centered">
              Search History
            </div>
            <v-chip-group column>
              <div class="control" v-for="(item, i) in recentSearch" :key="i">
                <v-chip
                  close
                  color="indigo lighten-2"
                  text-color="white"
                  close-icon="mdi-delete"
                  @click:close="onClickRemoveRecentSearchItem(item)"
                >
                  <span
                    @click="onClickSearchItem(item)"
                    style="cursor: pointer;"
                  >
                    {{ item }}
                  </span>
                </v-chip>
              </div>
            </v-chip-group>
          </div>
        </div>
      </div>
    </div>
    <v-snackbar v-model="flag" :timeout="2000" color="success">
      {{ itemText }}
      <template v-slot:action="{ attrs }">
        <v-btn dark text v-bind="attrs" @click="flag = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </section>
</template>

<script>
export default {
  name: "RecentSearch",
  props: {
    recentSearch: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      flag: false,
      itemText: ""
    };
  },
  computed: {
    searchBoxResize() {
      return {
        "is-3": this.recentSearch.length > 0 && this.recentSearch.length <= 3,
        "is-5": this.recentSearch.length >= 4 && this.recentSearch.length <= 8,
        "is-8": this.recentSearch.length >= 9
      };
    }
  },
  methods: {
    onClickRemoveRecentSearchItem(item) {
      this.$emit("clickRemoveRecentSearchItem", item);
      this.flag = true;
      this.itemText = `${item} 제거`;
    },
    onClickSearchItem(item) {
      if (item !== this.$store.state.albumStore.settings.searchQuery) {
        this.$emit("clickSearchItem", item);
      }
    }
  }
};
</script>

<style>
.tag-wrap {
  margin-top: 20px;
  padding: 10px 15px;
  background: #fff;
  border: 1px solid rgb(231, 231, 231);
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
</style>
