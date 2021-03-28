<template>
  <article id="panel-1" class="panel full-screen grey">
    <v-container fill-height>
      <v-row justify="center" ma-0>
        <h2>일반 검색</h2>
      </v-row>
      <v-row justify="center">
        <div class="searchBox">
          <input
            class="searchInput"
            type="text"
            name=""
            :placeholder="text"
            v-model="keyword"
            @keyup.enter="enterSearch"
          />

          <v-btn
            class="searchButton"
            color="#d1ebd9"
            depressed
            fab
            x-large
            :to="{ name: 'BasicSearch' }"
          >
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
      </v-row>
    </v-container>
  </article>
</template>

<script>
export default {
  data() {
    return {
      text: "음악을 검색 (버튼 클릭!) ==>",
      keyword: "",
    };
  },
  methods: {
    enterSearch() {
      //this.$store.state.albumStore.settings.initialSearchQuery = this.keyword;
      this.$store.commit("albumStore" + "/SET_INITIALSEARCH", this.keyword);
      console.log(this.$store.state.albumStore.settings.initialSearchQuery);
      this.$router.push({
        name: "BasicSearch",
      });
    },
    setQuery() {
      this.$store.state.albumStore.settings.initialSearchQuery = this.keyword;
    },
  },
};
</script>

<style scoped>
.description {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, 50%);
}
.searchBox {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, 50%);
  background: #2f3640;
  height: 90px;
  border-radius: 40px;
  padding: 10px;
}

.searchBox:hover > .searchInput {
  width: 440px;
  padding: 0 6px;
}

.searchBox:hover > .searchBtton {
  background: white;
  color: #d1ebd9;
}

.searchButton {
  float: right;
  background: #d1ebd9;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
}

.searchInput {
  border: none;
  background: none;
  outline: none;
  float: left;
  color: white;
  font-size: 1.5em;
  transition: 0.4s;
  line-height: 70px;
  width: 0px;
  transform: translateX(20px);
}

@media screen and (max-width: 1250px) {
  .searchBox:hover > .searchInput {
    width: 300px;
    padding: 0 6px;
  }
}
</style>
