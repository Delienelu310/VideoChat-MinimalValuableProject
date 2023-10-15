import { useEffect } from "react";
import { useAuth } from "../security/AuthContext";
import { useNavigation, useParams } from "react-router-dom";
import { getRoom } from "../api/RoomApi";

export default function Room(){

    const {roomId} = useParams();
    const {username, isAuthenticated} = useAuth();
    const navigate = useNavigation();

    const [room, setRoom] = useState(null);

    useEffect(() => {
        if(!isAuthenticated) navigate("/");

        getRoom({roomId})
            .then(response => {
                if(response.status != 200) navigate("/");

                setRoom(response.data);
            })
            .catch(e => navigate("/"));
    });

    return (
        <div className="video-chat-page">
            <h3>{room.roomDetails.title} - <b>room.id</b></h3>
        </div>
    );
}