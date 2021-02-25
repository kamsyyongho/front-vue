module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      '^/api': {
        target: 'http://localhost:8090',
        ws: true,
        changeOrigin: true
      }
    }
    // proxy: 'http://localhost:8090'
  },
  outputDir: '../src/main/resources/static'
}
    