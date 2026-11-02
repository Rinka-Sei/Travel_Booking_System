You are an expert Java developer. I have provided four context files:
1. project_roster_and_topics.md
2. starter_architecture_guide.md
3. master_compliance_blueprint.md
4. llm_code_generation_spec.md

I am assigning you **Item 9: Travel Booking System** (refer to `project_roster_and_topics.md` for the specific entities, features, and DSA requirements). 

Please generate the complete baseline implementation for this project. You MUST strictly adhere to the compliance rules in `master_compliance_blueprint.md` (5-tier structure, zero external libraries, `setLayout(null)`, admin-only DB seed). 

**Crucial Instructions:**
1. Use Java Reflection in the `service/` layer to decouple the `ds/` classes (as permitted in Section 9 of the blueprint).
2. Follow the output sequence and formatting rules exactly as dictated in `llm_code_generation_spec.md`.
3. Provide the complete directory tree first.
4. Provide the code for the Database, Model, DSA, Service, and GUI layers.
5. Provide the exact Git commands to create the branch, commit the code, and open a Pull Request.

Start by outputting the Directory Tree, then proceed to the Database and Model layers.