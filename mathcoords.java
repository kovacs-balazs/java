int minX = Math.min(-201, -200);
int minY = Math.min(61, 63);
int minZ = Math.min(-15, -17);
int maxX = Math.max(-201, -200);
int maxY = Math.max(61, 63);
int maxZ = Math.max(-15, -17);

for(int x = minX; x <= maxX; x++) {
    for (int y = minY; y <= maxY; y++) {
        for (int z = minZ; z <= maxZ; z++) {
            Location loc = new Location(world, x, y, z);
            loc.getBlock().setType(Material.AIR);
        }
    }
}
