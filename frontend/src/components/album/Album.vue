<template>
  <v-card
    class="mx-auto"
    color="#b5ac9a"
    dark
    :width="size.width"
    :height="size.height"
  >
    <AlbumTitle />
    <AlbumAnimation />
    <AlbumInfo />
    <AlbumControls />
  </v-card>
</template>

<script>
import AlbumTitle from "./AlbumTitle";
import AlbumAnimation from "./AlbumAnimation";
import AlbumInfo from "./AlbumInfo";
import AlbumControls from "./AlbumControls";

export default {
  props: ["size"],
  components: {
    AlbumTitle,
    AlbumAnimation,
    AlbumInfo,
    AlbumControls
  }
};
</script>

<style></style>
