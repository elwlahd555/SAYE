<template>
  <article id="panel-1" class="panel full-screen grey">
    <v-container fill-height>
      <v-row justify="center" ma-0>
        <h2>일반 검색</h2>
      </v-row>
      <v-row justify="center">
        <div class="description" v-show="scY <= 80">
          <p>
            듣고 싶은 음악을 검색하세요. [Spotify API를 사용한 결과 표시]
          </p>
        </div>
        <div class="searchBox" v-show="scY > 80">
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
            @click="setQuery"
            :to="{ name: 'BasicSearch' }"
          >
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
        {{ $store.state.albumStore.settings }}
      </v-row>
    </v-container>
  </article>
</template>

<script>
export default {
  data() {
    return {
      scY: 0,
      text: "음악을 검색 (버튼 클릭!) ==>",
      keyword: ""
    };
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  methods: {
    handleScroll() {
      this.scY = window.scrollY;
    },
    enterSearch() {
      this.$store.state.albumStore.settings.initialSearchQuery = this.keyword;
      console.log(this.$store.state.albumStore.settings.initialSearchQuery);
      this.$router.push({
        name: "BasicSearch"
      });
    },
    setQuery() {
      this.$store.state.albumStore.settings.initialSearchQuery = this.keyword;
    }
  }
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
