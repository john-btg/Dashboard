import {Link} from "react-router-dom";
import React, {Fragment, useState} from 'react'
import Axios from 'axios';

export default function Access_token() {

    const [Affiche,
        setAffiche] = useState('');


    const authentification = async () => {

            /*Création d'une requête appelant la route login */
            let res = await Axios({
                method: "GET",
                url: "http://localhost:8080/hello",
                header:{
                    'Access-Control-Allow-Origin': true,
                }
            })
        setAffiche(res.data);
        console.log(res.data + "coucou");
    }


    return (
        <Fragment>
            <button onClick={()=>authentification()}> coucou</button>
            <div>{Affiche}</div>
        </Fragment>
        )
}
