import React, { useState } from 'react';
import axios from 'axios';

const ProjectCreateForm = () => {
    // State pour stocker les données du formulaire
    const [projectData, setProjectData] = useState({
        title: '',
        description: ''
    });

    // Fonction pour gérer les changements dans le formulaire
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProjectData({
            ...projectData,
            [name]: value
        });
    };

    // Fonction pour soumettre le formulaire
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // Envoi des données du projet au backend
            const response = await axios.post('http://localhost:8080/api/projects', projectData);
            console.log('Projet créé avec succès :', response.data);

            // Réinitialiser le formulaire après la création
            setProjectData({
                title: '',
                description: ''
            });
        } catch (error) {
            console.error('Erreur lors de la création du projet :', error);
        }
    };

    return (
        <div>
            <h2>Créer un nouveau projet</h2>
            <form onSubmit={handleSubmit}>
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
                <button type="submit">Créer le projet</button>
            </form>
        </div>
    );
};

export default ProjectCreateForm;
