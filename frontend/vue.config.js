module.exports = {
  transpileDependencies: ["vuetify"],
  chainWebpack: config => {
    config.plugin("html").tap(args => {
      args[0].title = "Saye";
      return args;
    });
  },
  outputDir: "dist",
  devServer: {
    proxy: {
      "/api/": {
        target: "https://itunes.apple.com",
        changeOrigin: true,
        pathRewrite: { "^/api/": "" }
      }
    }
  },
  productionSourceMap: false
};
