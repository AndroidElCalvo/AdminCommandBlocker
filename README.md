# **AdminCommandBlocker v1.0**

A **Minecraft security plugin** that blocks specific commands. **Even players with OP or all permissions cannot use the blocked commands** unless they are added to the plugin’s whitelist.

---

## **Features**
- Permission-insensitive command blocking (blocks commands even for OP players).
- Built-in whitelist management.
- Easy configuration via `config.yml`.
- Prevents the plugin from being disabled with plugins like PlugMan.  

---

## **Installation**
1. Place the `AdminCommandBlocker-1.0.jar` file into your server’s `plugins` folder.
2. Start or restart your server to generate the default `config.yml`.
3. Edit `config.yml` to configure blocked commands and whitelist.
4. Ensure `/acb reload` permission (`admincommandblocker.reload`) is granted to allowed users.

---

## **Configuration Example (`config.yml`)**

```yaml
BanCommand: "/ban {player} Using blocked commands"

Commands:
  - "//sphere"
  - "//br"
  - "//brush"
  - "//cyl"
  - "//set"
  - "/ept"
  - "/pt"
  - "//replace"
  - "//hsphere"
  - "//fill"
  - "//fillr"
  - "/setblock"
  - "/execute"
  - "//generate"
  - "//spike"
  - "//rock"
  - "//calculate"
  - "//loft"
  - "//expand"
  - "//road"
  - "//deform"
  - "//air"
  - "//stack"

Whitelist:
  - "AdminPlayer"
```

- **CustomCommand**: Command executed when a player uses a blocked command.
- **Commands**: List of commands that are blocked.
- **Whitelist**: Players who can bypass the plugin.

---

## **Usage Example**

- Player “NotAllowed” tries `//sphere lava 5` → Command is blocked and ban executed automatically.
- Player “AdminPlayer” (in whitelist) can use `//sphere lava 5` without issues.

---

## **Support**
For suggestions or support, contact me via Discord or email.

**Support Discord:** [SkibidiGrief](https://dsc.gg/SkibidiGrief)  

**Contact:**
- Discord: @Android1Troll
- Email: [atrollcalvo823@gmail.com](mailto:atrollcalvo823@gmail.com)

---

### 🔹 Optional Notes
- This plugin does **not modify server files or configs**.
- Always keep a backup of your server before testing new plugins.
