<template>
  <v-card color="#3f3f3f" dark :loading="isUpdating">
    <template v-slot:progress>
      <v-progress-linear
        absolute
        color="green lighten-3"
        height="4"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-cae>
      <v-row>
        <!-- <v-col class="text-right" cols="12">
          <v-menu bottom left transition="slide-y-transition">
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon v-bind="attrs" v-on="on">
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="isUpdating = true">
                <v-list-item-action>
                  <v-icon>mdi-cog</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>Update</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-col> -->
        <v-row class="pa-4" align="center" justify="center">
          <v-col class="text-center">
            <h1>
              Contact Us
            </h1>
            <span class="grey--text text--lighten-1"
              >We'd love to hear from you, please drop us a line if you've any
              query</span
            >
          </v-col>
        </v-row>
      </v-row>
    </v-cae>

    <v-spacer></v-spacer>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="12">
            <v-text-field
              v-model="contact.name"
              :disabled="isUpdating"
              filled
              color="blue-grey lighten-2"
              label="Enter your Name"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              v-model="contact.email"
              :disabled="isUpdating"
              filled
              color="blue-grey lighten-2"
              label="Enter a valid email address"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-textarea
              v-model="contact.msg"
              :disabled="isUpdating"
              filled
              color="blue-grey lighten-2"
              label="Enter message"
            ></v-textarea>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-divider></v-divider>
    <v-card-actions>
      <!-- <v-switch
        v-model="autoUpdate"
        :disabled="isUpdating"
        class="mt-0"
        color="green lighten-2"
        hide-details
        label="Auto Update"
      ></v-switch> -->
      <v-spacer></v-spacer>
      <v-btn
        :loading="isUpdating"
        color="green darken-3"
        depressed
        @click="isUpdating = true"
      >
        <v-icon left>
          mdi-send
        </v-icon>
        Send Message
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      autoUpdate: true,
      isUpdating: false,
      contact: {
        name: "",
        email: "",
        msg: ""
      }
    };
  },

  watch: {
    isUpdating(val) {
      if (val) {
        setTimeout(() => (this.isUpdating = false), 3000);
      }
    }
  },

  methods: {
    remove(item) {
      const index = this.friends.indexOf(item.name);
      if (index >= 0) this.friends.splice(index, 1);
    }
  }
};
</script>
