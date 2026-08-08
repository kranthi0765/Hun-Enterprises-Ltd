import React, { useState } from "react";
import FormInput from "./FormInput";

function Signup() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username, email, password }),
            });
            const data = await response.json();
            if (response.ok) {
                console.log("Signup successful:", data);
                // Handle successful signup (e.g., redirect to login page)
                setUsername("");
                setEmail("");
                setPassword("");
            } else {
                console.error("Signup failed:" + data);
                // Handle signup failure (e.g., show error message)
            }
        } catch (error) {
            console.error("Error during signup:", error);
            // Handle network or other errors
            alert("An error occurred during signup. Please try again later.");
        }
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            <h2>Sign Up</h2>
            <FormInput label="Username" type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            <FormInput label="Email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <FormInput label="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <button type="submit" className="btn">Sign Up</button>
        </form>
    );
}

export default Signup;
