import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import { useAuth } from "../security/AuthContext";

import { getAllRooms, addRoom, enterRoom } from "../api/RoomApi";

export default function Welcome(){

    const {username, isAuthorised} = useAuth();

    const [error, setError] = useState(null);
    const [roomAdded, setRoomAdded] = useState(false);
    const navigate = useNavigate();

    //inputs
    const [title, setTitle] = useState();
    const [creationPassword, setCreationPassword] = useState(); 

    const [roomId, setRoomId] = useState();
    const [enteringPassword, setEnteringPassword] = useState();

    const [rooms, setRooms] = useState([]);

    function addNewRoom(){
        addRoom({title, username, password: creationPassword})
            .then(response => {
                if(response.status != 200) throw (response);

                setRoomAdded(true);
                refreshList();
            })
            .catch(error => setError(error));
    }

    function enterTheRoom(){
        enterRoom({roomId, username, password: enteringPassword})
            .then(response => {
                if(response.status != 200) throw (response);

                navigate(`/rooms/${roomId}`);
            })
            .catch(error => setError(error));
    }

    function refreshList(){
        getAllRooms()
            .then(response => {
                if(response.status != 200) throw (response);
                setRooms(response.data);
            }).catch(error => {
                setError(error);
            });
    }

    useEffect(refreshList, []);

    return (
        <div className="welcome m-5">
            <h2 className="m-2">Welcome {isAuthorised && username}</h2>
            <p className="m-2">To create or enter the room, you need to be logged in</p>
            
            {isAuthorised && 

                <div>
                    {error && 
                        <div className="m-2" style={{color: "red"}}>
                            {error}
                            <br/>
                            <button className="m-2 btn btn-danger" onClick={setError(null)}>Close</button>
                        </div> 
                    }

                    {roomAdded && 
                        <div className="m-2" style={{color: "green"}}>
                            <h4>The room was added successfully</h4>
                            <button className="btn btn-danger m-1" onClick={() => setRoomAdded(false)}>Close</button>
                        </div>
                    }
                    

                    <div className="m-2">
                        <h4 className="m-1">Create room</h4>
                        Title:
                        <input className="m-1 form-control" value={title} onChange={(e) => setTitle(e.target.value)}/>
                        Password:
                        <input className="m-1 form-control" value={creationPassword} onChange={(e) => setCreationPassword(e.target.value)}/>
                        <button className="m-2 btn btn-success" onClick={addNewRoom}>Create room</button>
                    </div>

                    <hr/>

                    <div className="m-2">
                        <h4 className="m-1">Enter room</h4>
                        Room id:
                        <input type="number" className="form-control m-1" value={roomId} onChange={(e) => setRoomId(e.target.value)}/>
                        Password:
                        <input className="form-control m-1" value={enteringPassword} onChange={(e) => setEnteringPassword(e.target.value)}/>
                        <button className="m-2 btn btn-success" onClick={enterTheRoom}>Enter</button>
                    </div>

                    <hr/>

                    <div className="m-5">
                        <h4 className="m-2">Rooms created:</h4>
                        {rooms.map((room, index)=> (
                            <div className="m-2" key={index}>
                                Room id: {room.id}
                                <br/>
                                Title: {room.roomDetails.title}
                                <br/>
                                Description: 
                                <p>{room.roomDetails.description}</p>
                                Admin: {room.admin.username}
                            </div>
                        ))}
                    </div>
                </div>
            }
        </div>
    );
}