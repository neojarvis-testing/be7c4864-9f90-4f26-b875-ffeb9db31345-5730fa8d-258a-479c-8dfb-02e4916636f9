import {configureStore} from '@reduxjs/toolkit';
import rootReducer from './userSlice';

const store = configureStore({
    reducer: rootReducer,
});

export default store;