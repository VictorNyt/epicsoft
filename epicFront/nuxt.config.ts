// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-04-03',
  devtools: { enabled: true },
  css: ["~/assets/css/app.css"],
  devServer: {host: '0.0.0.0', port: 3000, }
})
