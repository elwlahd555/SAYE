<template>
  <section class="section searchbar">
    <div class="container">
      <v-toolbar dark color="teal">
        <v-toolbar-title>Keyword</v-toolbar-title>
        <v-autocomplete
          size="is-medium"
          expanded
          flat
          hide-no-data
          hide-details
          solo-inverted
          class="mx-4"
          append-icon=""
          v-model="searchQuery"
          :data="filteredDataArray"
          label="e.g. 아이유"
          icon="magnify"
          @select="option => (selected = option)"
          @keyup.enter="onClickSearch"
        ></v-autocomplete>

        <v-btn x-large icon @click="onClickClearSearch">
          <v-icon>mdi-close-box-outline</v-icon>
        </v-btn>
      </v-toolbar>
    </div>
  </section>
</template>

<script>
import _ from "lodash";

export default {
  name: "TheSearchbar",
  data() {
    return {
      data: [],
      searchQuery: "",
      selected: null
    };
  },
  props: {
    recentSearch: {
      type: Array,
      required: true
    },
    newSearchQuery: {
      type: String,
      required: true
    },
    settings: {
      type: Object,
      required: true
    }
  },
  mounted() {
    //this.searchQuery = this.settings.initialSearchQuery;
    this.searchQuery = this.$route.query.keyword;
    this.onClickSearch();
  },
  watch: {
    searchQuery: {
      handler: _.debounce(function(val) {
        if (val === "") {
          this.$store.commit("CLEAR_SEARCH");
        } else {
          if (val !== this.newSearchQuery) {
            this.onClickSearch();
          }
        }
      }, 1000)
    },
    newSearchQuery(val) {
      this.searchQuery = val;
    }
  },
  computed: {
    filteredDataArray() {
      return this.recentSearch.filter(option => {
        return (
          option
            .toString()
            .toLowerCase()
            .indexOf(this.searchQuery.toLowerCase()) >= 0
        );
      });
    }
  },
  methods: {
    onClickSearch() {
      this.$emit("clickSearch", this.searchQuery);
    },
    onClickClearSearch() {
      this.searchQuery = "";
      this.$emit("clickClearSearch");
    }
  }
};
</script>

<style>
.searchbar {
  padding: 1rem 1.5rem !important;
  width: 100%;
  box-shadow: 0 0 70px 0 rgba(0, 0, 0, 0.3);
  background: #fff;
}
</style>
