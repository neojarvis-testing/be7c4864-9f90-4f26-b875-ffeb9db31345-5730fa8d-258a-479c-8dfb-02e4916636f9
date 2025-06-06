import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux'; // Import the Provider component
import store from './store.js'; // Import your Redux store
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'; // Import QueryClient and QueryClientProvider
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals'; 

const queryClient = new QueryClient(); // Create a QueryClient instance

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <QueryClientProvider client={queryClient}>
     
        <App />
     
    </QueryClientProvider>
  </Provider>
);

reportWebVitals();
