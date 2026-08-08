import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";

function App() {
    return (
        <div>
            <nav>
                <Link to="/login">Login</Link> | <Link to="/signup">Sign Up</Link>
            </nav>
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/signup" element={<SignupPage />} />
            </Routes>
        </div>
    );
}

export default App;
