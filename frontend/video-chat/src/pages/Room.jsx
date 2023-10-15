import { useEffect, useState } from "react";
import { useAuth } from "../security/AuthContext";
import { useNavigate, useParams } from "react-router-dom";
import { getRoom } from "../api/RoomApi";

export default function Room(){

    const {roomId} = useParams();
    const {username, isAuthorised} = useAuth();
    const navigate = useNavigate();

    const [room, setRoom] = useState(null);

    useEffect(() => {
        if(!isAuthorised) navigate("/");

        getRoom({roomId})
            .then(response => {
                if(response.status != 200){
                    console.log("invalid response status");
                    navigate("/");
                }

                setRoom(response.data);
            })
            .catch(e => {
                console.log(e);
                navigate("/")
            });
    }, []);

    return (
        <div className="video-chat-page">
            {room != null && 
                <div>
                    <h3>{room.roomDetails.title} - <b>room.id</b></h3>
                </div>
            }
        </div>
    );
}