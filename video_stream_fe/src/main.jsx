import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import IndexedDbContextProvider from './contexts/IndexedDbContextProvider.jsx'

createRoot(document.getElementById('root')).render(
  <IndexedDbContextProvider>
    <App />
  </IndexedDbContextProvider>
)
