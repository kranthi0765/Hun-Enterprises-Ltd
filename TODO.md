# TODO - Fix frontend/Mj-app (migrate from CRA to Vite)

## Task
Fix the broken React frontend at `frontend/Mj-app`. The CRA (create-react-app) + webpack toolchain has a corrupted `node_modules` causing cascading `MODULE_NOT_FOUND` errors. Migrate to Vite for a clean, reliable build.

## Steps
- [x] Analyze package.json (identified React 19 + react-scripts@5 incompatibility)
- [x] Downgrade `react`/`react-dom` to `^18.3.1` in package.json
- [x] Diagnosed corrupted node_modules (cascading MODULE_NOT_FOUND: underscore, webpack ./Dependency)
- [x] Approved plan to migrate to Vite
- [ ] Update `frontend/Mj-app/package.json` - replace react-scripts with vite + @vitejs/plugin-react, update scripts
- [ ] Create `frontend/Mj-app/vite.config.js`
- [ ] Create root `frontend/Mj-app/index.html` (Vite entry with module script)
- [ ] Rename `src/index.js` to `src/index.jsx`
- [ ] Delete corrupted `node_modules` and stale `package-lock.json`
- [ ] Run fresh `npm install`
- [ ] Verify `npm run build` succeeds
