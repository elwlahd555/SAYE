<template>
  <div class="current-track">
    <div class="song-title">Love poem</div>
    <div class="song-artist">IU (아이유)</div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.current-track {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  .song-title {
    width: 100vw;
    text-align: center;
    font-size: 2.4rem;
  }

  .song-artist {
    width: 100vw;
    text-align: center;
    font-size: 1.4rem;
    padding: 5px 0;
  }
}
</style>
