import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ProjectEditForm = ({ projectId}) => {
    // State pour stocker les données du projet
    const [projectData, setProjectData] = useState({
        title: '',
        description: ''
    });

    // Effet pour récupérer les données du projet à modifier
    useEffect(() => {
        const fetchProjectData = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/projects/${projectId}`);
                setProjectData(response.data);
            } catch (error) {
                console.error('Erreur lors de la récupération des données du projet :', error);
            }
        };

        fetchProjectData();
    }, [projectId]);

    // Fonction pour gérer les changements dans le formulaire
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProjectData({
            ...projectData,
            [name]: value
        });
    };

    // Fonction pour soumettre le formulaire de mise à jour
    const handleUpdate = async (e) => {
        e.preventDefault();
        try {
            // Envoi des données mises à jour au backend
            await axios.put(`http://localhost:8080/api/projects/${projectId}`, projectData);
            console.log('Projet mis à jour avec succès.');

        } catch (error) {
            console.error('Erreur lors de la mise à jour du projet :', error);
        }
    };

    return (
        <div>
            <h2>Modifier le projet</h2>
            <form onSubmit={handleUpdate}>
                <div>
                    <label htmlFor="title">Titre du projet:</label>
                    <input
                        type="text"
                        id="title"
                        name="title"
                        value={projectData.title}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="description">Description du projet:</label>
                    <textarea
                        id="description"
                        name="description"
                        value={projectData.description}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <button type="submit">Mettre à jour le projet</button>
            </form>
        </div>
    );
};

export default ProjectEditForm;
