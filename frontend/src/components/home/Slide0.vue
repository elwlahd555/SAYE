<template>
  <section id="intro" class="full-screen pt-5 black">
    <Row title="Trending Videos" :fetchUrl="requests.fetchTrending" />
    <Row title="Top Rated" :fetchUrl="requests.fetchTopRated" />
    <Row title="Romance Movies" :fetchUrl="requests.fetchRomanceMovies" />
  </section>
</template>

<script>
import Row from "@/components/home/Slide0/Row";
import requests from "@/plugins/requests";

export default {
  data() {
    return {
      requests
    };
  },
  components: {
    Row
  }
};
</script>

<style></style>
