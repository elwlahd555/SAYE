<template>
  <section class="section searchbar">
    <v-container>
      <v-row>
        <v-col cols="11">
          <v-text-field
            outlined
            label="Keyword"
            type="text"
            v-model="searchQuery"
            :data="filteredDataArray"
            @select="option => (selected = option)"
            @keyup.enter="onClickSearch"
          >
            <template v-slot:prepend>
              <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                  <v-icon v-on="on">
                    mdi-help-circle-outline
                  </v-icon>
                </template>
                Search Keyword
              </v-tooltip>
            </template>
            <template v-slot:append>
              <v-fade-transition leave-absolute>
                <v-progress-circular
                  v-if="loading"
                  size="24"
                  color="info"
                  indeterminate
                ></v-progress-circular>
              </v-fade-transition>
            </template>
          </v-text-field>
        </v-col>
        <v-col cols="1">
          <v-btn x-large icon @click="onClickClearSearch">
            <v-icon>mdi-close-box-outline</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </section>
</template>

<script>
import _ from "lodash";

const albumStore = "albumStore";

export default {
  name: "TheSearchbar",
  data() {
    return {
      data: [],
      searchQuery: "",
      selected: null,
      loading: false
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
    this.searchQuery = this.settings.initialSearchQuery;
    //this.searchQuery = this.$route.query.keyword;
    this.onClickSearch();
  },
  watch: {
    searchQuery: {
      handler: _.debounce(function(val) {
        if (val === "") {
          this.$store.commit(albumStore + "/CLEAR_SEARCH");
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
      this.loading = true;
      this.$emit("clickSearch", this.searchQuery);
      console.log(this.searchQuery);
      setTimeout(() => {
        this.loading = false;
      }, 500);
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
