# SHULKER PREVIEW v0.1

This plugin enables players to preview their shulker boxes by  sneaking and left-clicking a shulker box as their selected hotbar item. Additionally, players can edit the shulker box, close it, and have the content saved to the corresponding held item.

Shulker Preview provides a permission-based system for exclusive usage. It is compatible with  popular plugins like  Luckperms, utilizing Vault to facilitate the connection.

### Commands
``
/shulkerpreview reload
`` — refreshes the plugin's config

### Config

```access transformers
# Shulker Preview Plugin
# Download releases - github.com/awokens/shulkerpreview/releases
# Contact on Discord - Awokens

# Toggle the ability to edit shulkers when previewing
toggle-shulker-edits: true

# Assign the ability to preview shulkers through a permission
# - Vault compatible (e.g. Luckperms)
shulker-preview-permission: ""  # Leave empty if no permission required
shulker-preview-permission-message: "&cNo permission to preview shulkers"  # Can be left empty for no permission messages

# Admin stuff
config-reload-permission: ""  # Leave empty if no permission required
config-reload-permission-message: "&cNo permission to reload ShulkerPreview config"  # Can be left empty for no permission messages
```
