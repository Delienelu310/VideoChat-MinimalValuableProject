import { apiClient } from "./ApiClient";

export function getRoom({roomId}){
    return apiClient.get(`/rooms/${roomId}`);
}

export function getAllRooms(){
    return apiClient.get("/rooms");
}

export function deleteRoom({roomId}){
    return apiClient.delete(`/rooms/${roomId}`);
}

export function addRoom({title, password, username}){
    return apiClient.post(`/users/${username}/rooms`, {roomPassword: password, roomDetails: {title}});
}

export function enterRoom({roomId, password, username}){
    return apiClient.put(`/rooms/${roomId}/add/participant/${username}`, {roomDetails: {title: "title"}, roomPassword: password})
}

export function exitRoom({roomId, username}){
    return apiClient.put(`/rooms/${roomId}/remove/participant/${username}`);
}