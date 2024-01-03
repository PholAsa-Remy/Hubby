import './App.css'
import ProjectList from "./ProjectList.jsx";
import ProjectCreateForm from "./ProjectCreateForm.jsx";
import ProjectEditForm from "./ProjectEditForm.jsx";
function App() {
  return (
    <>
        <p>Hello World</p>
        <ProjectCreateForm/>
        <ProjectList/>
        <ProjectEditForm projectId={2}/>

    </>
  )
}

export default App
