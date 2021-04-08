<template>
  <div class="vinyl">
    <div class="vinyl-container">
      <div class="vinyl-record">
        <div class="vinyl-record-image">
          <div class="vinyl-record-hole"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
@mixin flex {
  display: flex;
  justify-content: center;
  align-items: center;
}

.vinyl {
  @include flex;
}

.vinyl-container {
  border-radius: 50%;
  padding: 16px;
  background-color: #eee;
  box-shadow: inset 0 1px 2px rgba($color: #000000, $alpha: 0.4),
    0 -1px 1px #fff, 0 1px 0 #fff;
  border-bottom: 1px solid #ddd;
}

.vinyl-record {
  @include flex;
  border-radius: 50%;
  width: 40vh;
  height: 40vh;
  background: linear-gradient(
        10deg,
        transparent 40%,
        rgba($color: #333, $alpha: 0.8) 40%
      )
      no-repeat 100% 0,
    linear-gradient(80deg, rgba($color: #333, $alpha: 0.8) 60%, transparent 60%)
      no-repeat 0 100%,
    repeating-radial-gradient(#333, #333 9px, #ccc 10px, #333 11px);
  background-size: 50% 100%, 100% 50%, 100%;
  animation: vinyl-moving 25s linear infinite;
}

.vinyl-record-image {
  @include flex;
  border: 1px solid #000;
  border-radius: 50%;
  width: calc(40vh - 60%);
  height: calc(40vh - 60%);
  background: url("../../assets/images/IU.png") no-repeat center;
  background-size: cover;
  //animation: image-rotate 5s linear infinite;
}

.vinyl-record-hole {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: #b5d0e6;
}

@keyframes image-rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

@keyframes vinyl-moving {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(1800deg);
  }
}
</style>
