import axios from "axios";

const api = axios.create({
    baseURL: 'https://8080-fbdfcfebebefebefccadafadacdfbef.project.examly.io',
    headers: {"Content-Type":"application/json"}
});

export default api;