<template>
  <v-container fluid>
    <Album :size="size" />
  </v-container>
</template>

<script>
import Album from "@/components/album/Album";

export default {
  components: {
    Album
  },
  data() {
    return {
      size: {
        width: 500,
        height: 600
      }
    };
  }
};
</script>

<style></style>
