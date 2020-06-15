import Axios from "axios";

//TODO, fix it
export const APP_CONTENT_ROOT = "/template/project/url";

const APP_CONTENT_ROOT_REST = `${APP_CONTENT_ROOT}/rest`

if (window.location.port === "3000") {
   Axios.defaults.baseURL = `http://localhost:3500${APP_CONTENT_ROOT_REST}`;
} else {
  const wl = window.location; //to shorten
  Axios.defaults.baseURL = `${wl.protocol}//${wl.hostname}:${wl.port}${APP_CONTENT_ROOT_REST}`
}

export default class RestService {

  constructor(errorCallback) {
    this.errorCallback = errorCallback;
  }

  uploadFile(url, file, successHandler, errorHandler) {
    const data = new FormData();
    data.append("file", file);
    Axios.request({
      //do not overwrite baseURL here, by default it sent to the server, where it is deployed
      method: "post",
      url: url,
      data: data,
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }).then(successHandler)
      .catch(error => {
        errorHandler(error);
        this.errorHandler(error);
      });
  }

  delete(url, callback){
    this.sendRequest("delete", url, callback);
  }

  post(url, data, callback) {
    this.sendDataRequest(
      "post",
      url,
      data,
      callback);
  }

  getData(url, callback) {
    this.sendRequest("get", url, callback);
  }

  sendDataRequest(method, url, data, callback) {
    Axios.request({
      method: method,
      url: url,
      data: data
    }).then(response => callback(response.data))
      .catch(this.errorHandler);
  }

  sendRequest(method, url, callback) {
    Axios.request({
      method: method,
      url: url
    }).then(response => callback(response.data))
      .catch(this.errorHandler);
  }

  errorHandler = error => {
    var descr = error.response && error.response.data ? error.response.data : error;
    console.log("error: " + descr);
    this.errorCallback(descr);
  }
}