import {useEffect, useState} from "react";
import axios from 'axios';

export default function ProjectList () {
    const [projects, setProjects] = useState([]);

    /* Get Project List */
    useEffect(() => {
        const fetchProjectList = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/projects');
                console.log(response);
                setProjects(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchProjectList();
    }, []);

    return (
        <div>
            <h3>Projects List : </h3>
            <ul>
                {projects.map((item) => (
                    <li key={item.id}>{item.title}</li>
                ))}
            </ul>
        </div>
    )
}