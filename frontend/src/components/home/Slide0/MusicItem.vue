<template>
  <div class="music_video_list_item">
    <div class="music_video_list_item_wrap">
      <div>
        <div
          class="music_video_list_item_thumbnail_cover"
          v-bind:class="{ active: music.isActive }"
          @mouseenter="music.isActive = true"
          @mouseleave="music.isActive = false"
          @mousemove="music.isActive = true"
          @click="$emit('play', music.mPreview)"
        >
          <div class="music_video_list_item_thumbnail_cover_img">
            <v-icon color="indigo" size="100" style="top:35%; left:35%">
              mdi-play-circle-outline
            </v-icon>
          </div>
        </div>
        <img
          class="music_video_list_item_thumbnail"
          @mouseenter="music.isActive = true"
          @mouseleave="music.isActive = false"
          :src="music.mImg"
          :alt="music.mTitle"
        />
      </div>
      <div class="music_video_list_item_title_div">
        <router-link
          :to="'/music/' + music.mId"
          class="music_video_list_item_music_name"
          >{{ music.mTitle }}</router-link
        >
        <router-link
          :to="'/artist/' + music.artistId"
          class="music_video_list_item_artist"
          >{{ music.mArtist }}</router-link
        >
        <span
          v-if="music.mDate !== undefined"
          class="music_video_list_item_release_date"
          >{{ music.mDate }}</span
        >
      </div>
      <div class="music_video_list_item_fav_div">
        <v-icon
          dark
          v-if="music.isFavorite"
          class="music_video_list_item_fav_img on"
          alt="즐겨찾기"
          @click="$emit('favorite', music.mId, false)"
        >
          mdi-heart-outline
        </v-icon>
        <v-icon
          dark
          v-else
          class="music_video_list_item_fav_img"
          alt="즐겨찾기 취소"
          @click="$emit('favorite', music.mId, true)"
        >
          mdi-heart
        </v-icon>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MusicItem",
  props: {
    music: Object
  },
  computed: {}
};
</script>

<style scoped lang="scss">
.music_video_list_item {
  & {
    width: 25%;
    padding: 0 7px 30px;
    display: inline-block;
    vertical-align: top;
  }

  .music_video_list_item_wrap {
    & {
      position: relative;
      font-size: 12pt;
      border-bottom: none;
      display: block;
      text-align: left;
      border-radius: 6px;
      background-color: rgba(0, 0, 0, 0.25);
      outline: 0;
      transition: all 0.2s ease;
    }
    &:hover {
      background-color: rgba(0, 0, 0, 0.15);
      box-shadow: inset 0 0 0 1px rgba(255, 255, 255, 0.25), 0 0 0.5em 0 #aaaaaa;
    }
    & > div {
      position: relative;
      display: flex;
    }
    .music_video_list_item_thumbnail {
      width: 100%;
      cursor: pointer;
    }
    .music_video_list_item_thumbnail_cover {
      background: rgba(0, 0, 0, 0.4);
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;
      border-top-left-radius: 6px;
      border-top-right-radius: 6px;
      opacity: 0;
      cursor: pointer;
      transition: opacity 0.3s ease;
    }
    .music_video_list_item_thumbnail_cover.active {
      opacity: 1;
    }
    .music_video_list_item_thumbnail_cover_img {
      width: 100%;
      height: 100%;
      position: absolute;
    }

    .music_video_list_item_title_div {
      width: calc(100% - 44px);
      position: relative;
      display: inline-block;
      padding: 5px 15px 7px;
      letter-spacing: 0.4px;
      line-height: 1;
    }
    .music_video_list_item_title_div > span {
      display: block;
      white-space: nowrap;
      text-overflow: ellipsis;
      overflow: hidden;
      width: 100%;
    }
    .music_video_list_item_music_name {
      display: block;
      font-weight: 400;
      padding-bottom: 2px;
      font-size: 18px;
      line-height: 32px;
      white-space: nowrap;
      text-overflow: ellipsis;
      overflow: hidden;
      cursor: pointer;
      max-width: 100%;
      color: #ffffff !important;
    }
    .music_video_list_item_music_name:hover {
      text-decoration: underline;
    }
    .music_video_list_item_artist {
      display: inline-block;
      font-weight: 400;
      font-size: 14px;
      line-height: 18px;
      white-space: nowrap;
      text-overflow: ellipsis;
      overflow: hidden;
      cursor: pointer;
      max-width: 100%;
      color: #ffffff !important;
    }
    .music_video_list_item_artist:hover {
      text-decoration: underline;
    }
    .music_video_list_item_release_date {
      display: block;
      padding-bottom: 2px;
      font-size: 14px;
      line-height: 18px;
    }
    .music_video_list_item_fav_div {
      display: inline-block;
      padding: 10px 10px 0 0;
      letter-spacing: 0.4px;
      vertical-align: top;
      float: right;
      line-height: 4;
    }
    .music_video_list_item_fav_img {
      width: 32px;
      padding: 4px;
      margin-top: -4px;
      vertical-align: middle;
      cursor: pointer;
    }
    .music_list_item_play_div {
      display: inline-block;
      padding: 7px 0 7px 0;
      letter-spacing: 0.4px;
      vertical-align: top;
      float: right;
      line-height: 3;
    }
    .music_list_item_play {
      width: 32px;
      padding: 5px;
      margin-top: -4px;
    }

    .music_item_arrow_div {
      display: inline-block;
      vertical-align: top;
    }
    .music_item_arrow {
      width: 12px;
      margin: 3px 0 0 5px;
      vertical-align: top;
      cursor: pointer;
    }

    .music_item_singer_div {
      position: absolute;
      width: 160px;
      margin: 3px 0 0 -70px;
      padding: 2px 15px;
      border: 1px solid #fff;
      background: #444;
      z-index: 1;
    }
    .music_item_singer_item {
      position: relative;
      display: inline-block;
      margin: 0;
      padding: 0;
      cursor: pointer;
      line-height: 22px;
      vertical-align: top;
      font-size: 15px;
      color: #ffffff !important;
      transition: initial;
    }
    .music_item_singer_item:hover {
      text-decoration: underline;
    }
  }
}
</style>
