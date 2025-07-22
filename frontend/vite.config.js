import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { visualizer } from 'rollup-plugin-visualizer'

// https://vite.dev/config/
export default defineConfig({
  plugins: [ react(),
    visualizer({
      open: true, // Automatically opens the browser
      filename: 'bundle-stats.html', // Output file
      gzipSize: true,
      brotliSize: true,
    })
  ],
  server: {
    proxy: {
      '/api': 'http://localhost:8080', // Proxy to Spring Boot
    }
  }
})
