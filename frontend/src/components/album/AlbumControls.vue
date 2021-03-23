<template>
  <v-card class="mx-auto" color="#97a4b8" outlined>
    <v-list-item three-line>
      <v-list-item-content>
        <div class="overline mb-4">
          Controller
        </div>
        <v-list-item-title class="headline mb-1">
          아님 여기다 제목만 표시할까?
        </v-list-item-title>
        <v-list-item-subtitle>여기는 그냥 간단한 설명?</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>
      <v-row justify="center">
        <v-btn color="info">Pre</v-btn>
        <v-btn color="success">Play</v-btn>
        <v-btn color="warning">Pause</v-btn>
        <v-btn color="error">Stop</v-btn>
        <v-btn color="info">Next</v-btn>
      </v-row>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {};
</script>

<style></style>
